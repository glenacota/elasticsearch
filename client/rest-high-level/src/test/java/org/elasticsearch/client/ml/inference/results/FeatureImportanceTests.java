/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.client.ml.inference.results;

import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.test.AbstractXContentTestCase;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FeatureImportanceTests extends AbstractXContentTestCase<FeatureImportance> {

    @Override
    protected FeatureImportance createTestInstance() {
        return new FeatureImportance(
            randomAlphaOfLength(10),
            randomBoolean() ? null : randomDoubleBetween(-10.0, 10.0, false),
            randomBoolean() ? null :
                Stream.generate(() -> randomAlphaOfLength(10))
                    .limit(randomLongBetween(2, 10))
                    .map(name -> new FeatureImportance.ClassImportance(name, randomDoubleBetween(-10, 10, false)))
                    .collect(Collectors.toList()));

    }

    @Override
    protected FeatureImportance doParseInstance(XContentParser parser) throws IOException {
        return FeatureImportance.fromXContent(parser);
    }

    @Override
    protected boolean supportsUnknownFields() {
        return true;
    }

}
