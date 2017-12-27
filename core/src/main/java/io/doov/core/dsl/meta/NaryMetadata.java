/*
 * Copyright 2017 Courtanet
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.doov.core.dsl.meta;

import static io.doov.core.dsl.meta.DefaultOperator.match_all;
import static io.doov.core.dsl.meta.DefaultOperator.match_any;
import static io.doov.core.dsl.meta.MetadataType.NARY_PREDICATE;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

import io.doov.core.dsl.lang.Context;
import io.doov.core.dsl.meta.ast.AstVisitorUtils;

public class NaryMetadata extends PredicateMetadata {
    private final Operator operator;
    private final List<Metadata> values;

    private NaryMetadata(Operator operator, List<Metadata> values) {
        this.operator = operator;
        this.values = values;
    }

    public Operator getOperator() {
        return operator;
    }

    public static NaryMetadata matchAnyMetadata(List<Metadata> values) {
        return new NaryMetadata(DefaultOperator.match_any, values);
    }

    public static NaryMetadata matchAllMetadata(List<Metadata> values) {
        return new NaryMetadata(DefaultOperator.match_all, values);
    }

    public static NaryMetadata matchNoneMetadata(List<Metadata> values) {
        return new NaryMetadata(DefaultOperator.match_none, values);
    }

    public static NaryMetadata countMetadata(List<Metadata> values) {
        return new NaryMetadata(DefaultOperator.count, values);
    }

    public static NaryMetadata sumMetadata(List<Metadata> values) {
        return new NaryMetadata(DefaultOperator.sum, values);
    }

    public static NaryMetadata minMetadata(List<Metadata> values) {
        return new NaryMetadata(DefaultOperator.min, values);
    }

    @Override
    public String readable() {
        return AstVisitorUtils.astToString(this);
    }

    @Override
    public void accept(MetadataVisitor visitor) {
        visitor.start(this);
        values.forEach(v -> {
            v.accept(visitor);
            visitor.visit(this);
        });
        visitor.end(this);
    }

    @Override
    public PredicateMetadata merge(FieldMetadata other) {
        return new NaryMetadata(new ComposeOperator(operator, other), values);
    }

    @Override
    public List<Metadata> childs() {
        return Collections.unmodifiableList(values);
    }

    @Override
    public MetadataType type() {
        return NARY_PREDICATE;
    }

    @Override
    public Metadata message(Context context) {
        if (operator == match_all && context.isEvalFalse(this)) {
            return new EmptyMetadata();
        } else if (operator == match_any) {
            final List<Metadata> childMsgs = values.stream()
                            .filter(md -> context.isEvalTrue(md))
                            .map(md -> md.message(context)).collect(toList());
            if (childMsgs.size() == 1)
                return childMsgs.get(0);
            return new NaryMetadata(operator, childMsgs);
        }
        return new NaryMetadata(operator, values.stream().map(md -> md.message(context)).collect(toList()));
    }
}