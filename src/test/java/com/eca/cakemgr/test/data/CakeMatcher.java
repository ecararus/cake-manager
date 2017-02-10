package com.eca.cakemgr.test.data;

import com.eca.cakemgr.domain.Cake;
import org.hamcrest.Matcher;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public final class CakeMatcher {

    public CakeMatcher() {
        throw new UnsupportedOperationException();
    }

    public static <T> Matcher<T> matchesWith(Cake cake) {
        checkNotNull(cake);
        return allOf(
                hasProperty("title", equalTo(cake.getTitle())),
                hasProperty("desc", equalTo(cake.getDesc())),
                hasProperty("image", equalTo(cake.getImage()))
        );
    }

}
