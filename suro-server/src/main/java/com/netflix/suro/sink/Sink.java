/*
 * Copyright 2013 Netflix, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.netflix.suro.sink;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.netflix.suro.message.Message;
import com.netflix.suro.sink.kafka.KafkaSink;
import com.netflix.suro.sink.localfile.LocalFileSink;
import com.netflix.suro.sink.remotefile.S3FileSink;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(name = LocalFileSink.TYPE, value = LocalFileSink.class),
        @JsonSubTypes.Type(name = S3FileSink.TYPE, value = S3FileSink.class),
        @JsonSubTypes.Type(name = KafkaSink.TYPE, value = KafkaSink.class)
})
public interface Sink {
    void writeTo(Message message);

    void open();
    void close();
    String recvNotify();
    String getStat();
}