/*
 * Copyright (c) 2013, 2014, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.sun.prism.impl;

import com.sun.prism.GraphicsResource;
import java.util.ArrayList;

public abstract class ManagedResource<T> implements GraphicsResource {
    static final boolean trackLockSources = false;

    static boolean _isgone(ManagedResource<?> mr) {
        if (mr == null) return true;
        if (mr.disposalRequested) {
            mr.free();
            mr.resource = null;
            mr.disposalRequested = false;
            return true;
        }
        return !mr.isValid();
    }

    protected T resource;
    private final ResourcePool<T> pool;
    private int lockcount;
    private int employcount;
    ArrayList<Throwable> lockedFrom;
    private boolean permanent;
    private boolean mismatchDetected;
    private boolean disposalRequested;
    private int age;

    protected ManagedResource(T resource, ResourcePool<T> pool) {
        this.resource = resource;
        this.pool = pool;
        if (trackLockSources) {
            this.lockedFrom = new ArrayList<Throwable>();
        }
        manage();
        lock();
    }

    private void manage() {
        pool.resourceManaged(this);
    }

    public final T getResource() {
        assertLocked();
        return resource;
    }

    public final ResourcePool<T> getPool() {
        return pool;
    }

    public boolean isValid() {
        return resource != null && !disposalRequested;
    }

    public boolean isDisposalRequested() {
        return disposalRequested;
    }

    public final boolean isLocked() {
        return lockcount > 0;
    }

    public final int getLockCount() {
        return lockcount;
    }

    public final void assertLocked() {
        if (lockcount <= 0) {
            throw new IllegalStateException("Operation requires resource lock");
        }
    }

    public final boolean isPermanent() {
        return permanent;
    }

    public final boolean isInteresting() {
        return employcount > 0;
    }

    public final int getInterestCount() {
        return employcount;
    }

    public void free() {
    }

    public int getAge() {
        return age;
    }

    @Override
    public final void dispose() {
        if (pool.isManagerThread()) {
            T r = resource;
            if (r != null) {
                free();
                disposalRequested = false;
                resource = null;
                pool.resourceFreed(this);
            }
        } else {
            disposalRequested = true;
        }
    }

    public final void makePermanent() {
        assertLocked();
        permanent = true;
    }

    public final T lock() {
        lockcount++;
        age = 0;
        if (trackLockSources && !permanent) {
            lockedFrom.add(new Throwable(Integer.toString(lockcount)));
        }
        return resource;
    }

    void unlockall() {
        lockcount = 0;
        if (trackLockSources && !permanent) {
            lockedFrom.clear();
        }
    }

    public final void unlock() {
        assertLocked();
        lockcount--;
        if (trackLockSources && !permanent && lockcount == 0) {
            lockedFrom.clear();
        }
    }

    public final void contentsUseful() {
        assertLocked();
        employcount++;
    }

    public final void contentsNotUseful() {
        if (employcount <= 0) {
            throw new IllegalStateException("Resource obsoleted too many times");
        }
        employcount--;
    }

    public final boolean wasMismatched() {
        return mismatchDetected;
    }

    public final void setMismatched() {
        mismatchDetected = true;
    }

    public final void bumpAge(int forever) {
        int a = this.age;
        if (a < forever) {
            this.age = a + 1;
        }
    }
}
