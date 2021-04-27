/*
 * Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved.
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

package javafx.scene.text;

/**
Builder class for javafx.scene.text.Font
@see javafx.scene.text.Font
@deprecated This class is deprecated and will be removed in the next version
* @since JavaFX 2.0
*/
@javax.annotation.Generated("Generated by javafx.builder.processor.BuilderProcessor")
@Deprecated
public final class FontBuilder implements javafx.util.Builder<javafx.scene.text.Font> {
    protected FontBuilder() {
    }

    /** Creates a new instance of FontBuilder. */
    @SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
    public static javafx.scene.text.FontBuilder create() {
        return new javafx.scene.text.FontBuilder();
    }

    private java.lang.String name;
    /**
    Set the value of the {@link javafx.scene.text.Font#getName() name} property for the instance constructed by this builder.
    */
    public javafx.scene.text.FontBuilder name(java.lang.String x) {
        this.name = x;
        return this;
    }

    private double size;
    /**
    Set the value of the {@link javafx.scene.text.Font#getSize() size} property for the instance constructed by this builder.
    */
    public javafx.scene.text.FontBuilder size(double x) {
        this.size = x;
        return this;
    }

    /**
    Make an instance of {@link javafx.scene.text.Font} based on the properties set on this builder.
    */
    public javafx.scene.text.Font build() {
        javafx.scene.text.Font x = new javafx.scene.text.Font(this.name, this.size);
        return x;
    }
}
