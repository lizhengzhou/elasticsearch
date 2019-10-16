/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.core.ml.dataframe.analyses;

import org.elasticsearch.common.io.stream.NamedWriteable;
import org.elasticsearch.common.xcontent.ToXContentObject;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DataFrameAnalysis extends ToXContentObject, NamedWriteable {

    /**
     * @return The analysis parameters as a map
     */
    Map<String, Object> getParams();

    /**
     * @return {@code true} if this analysis supports fields with categorical values (i.e. text, keyword, ip)
     */
    boolean supportsCategoricalFields();

    /**
     * @param fieldName field for which the allowed categorical types should be returned
     * @return The types treated as categorical for the given field
     */
    Set<String> getAllowedCategoricalTypes(String fieldName);

    /**
     * @return The names and types of the fields that analyzed documents must have for the analysis to operate
     */
    List<RequiredField> getRequiredFields();

    /**
     * @return {@link Map} containing cardinality limits for the selected (analysis-specific) fields
     */
    Map<String, Long> getFieldCardinalityLimits();

    /**
     * @return {@code true} if this analysis supports data frame rows with missing values
     */
    boolean supportsMissingValues();

    /**
     * @return {@code true} if this analysis persists state that can later be used to restore from a given point
     */
    boolean persistsState();

    /**
     * Returns the document id for the analysis state
     */
    String getStateDocId(String jobId);
}