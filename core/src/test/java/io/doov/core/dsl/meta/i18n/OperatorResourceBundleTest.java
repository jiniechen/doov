/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package io.doov.core.dsl.meta.i18n;

import static io.doov.core.dsl.meta.DefaultOperator.always_true;
import static io.doov.core.dsl.meta.DefaultOperator.rule;
import static io.doov.core.dsl.meta.i18n.DefaultResourceBundle.BUNDLE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.jupiter.api.Test;

public class OperatorResourceBundleTest {
    
    @Test
    public void always_true() {
        assertThat(BUNDLE.get(always_true)).isEqualTo("always true");
    }
    
    @Test
    public void always_true_fr_FR() {
        assertThat(BUNDLE.get(always_true, Locale.FRANCE)).isEqualTo("toujours vrai");
    }
    
    @Test
    public void rule() {
        assertThat(BUNDLE.get(rule)).isEqualTo("rule");
    }
    
    @Test
    public void rule_fr_FR() {
        assertThat(BUNDLE.get(rule, Locale.FRANCE)).isEqualTo("règle");
    }
}