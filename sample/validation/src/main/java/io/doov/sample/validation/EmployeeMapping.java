/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package io.doov.sample.validation;

import static io.doov.core.dsl.DOOV.map;
import static io.doov.core.dsl.DOOV.mappings;
import static io.doov.core.dsl.mapping.TypeConverters.biConverter;
import static io.doov.core.dsl.mapping.TypeConverters.converter;
import static io.doov.sample.field.dsl.DslSampleModel.accountCompany;
import static io.doov.sample.field.dsl.DslSampleModel.accountCountry;
import static io.doov.sample.field.dsl.DslSampleModel.accountEmail;
import static io.doov.sample.field.dsl.DslSampleModel.userBirthdate;
import static io.doov.sample.field.dsl.DslSampleModel.userFirstName;
import static io.doov.sample.field.dsl.DslSampleModel.userLastName;
import static io.doov.sample3.field.dsl.Dslmployee.employeeAge;
import static io.doov.sample3.field.dsl.Dslmployee.employeeCompany;
import static io.doov.sample3.field.dsl.Dslmployee.employeeCountry;
import static io.doov.sample3.field.dsl.Dslmployee.employeeEmail;
import static io.doov.sample3.field.dsl.Dslmployee.employeeFullname;
import static java.time.temporal.ChronoUnit.YEARS;
import static java.util.Arrays.asList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

import io.doov.core.dsl.lang.MappingRule;

public class EmployeeMapping {
    public static final MappingRule FULLNAME_MAPPING = map(userFirstName, userLastName)
            .using(biConverter((firstName, lastName) -> firstName + " " + lastName, "", "combine names"))
            .to(employeeFullname);

    public static final MappingRule EMAIL_MAPPING = map(accountEmail).to(employeeEmail);

    public static final MappingRule AGE_MAPPING = map(userBirthdate)
            .using(converter(d -> (int) YEARS.between(d, LocalDate.of(2019, 1, 1)), "age at today"))
            .to(employeeAge);

    public static final MappingRule COUNTRY_MAPPING = map(accountCountry)
            .using(converter(c -> c.name(), "country not available", "country name"))
            .to(employeeCountry);

    public static final MappingRule COMPANY_MAPPING = map(accountCompany)
            .using(converter(c -> c.name(), "company not available", "company name"))
            .to(employeeCompany);

    public static final MappingRule ALL_MAPPINGS = mappings(FULLNAME_MAPPING,
            EMAIL_MAPPING,
            AGE_MAPPING,
            COUNTRY_MAPPING,
            COMPANY_MAPPING);

    public static void main(String[] args) throws IOException {
        SampleWriter.of("employee_mapping_rules.html", Locale.ENGLISH).write(asList(ALL_MAPPINGS));
        System.exit(1);
    }

}
