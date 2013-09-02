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

package com.netflix.suro.sink.localfile;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.netflix.suro.message.Message;

import java.io.IOException;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(name = SequenceFileWriter.TYPE, value = SequenceFileWriter.class),
        @JsonSubTypes.Type(name = TextFileWriter.TYPE, value = TextFileWriter.class)
})
public interface FileWriter {
    void open(String outputDir) throws IOException;
    long getLength() throws IOException;
    void writeTo(Message message) throws IOException;
    void sync() throws IOException;
    void rotate(String newPath) throws IOException;

    void close() throws IOException;
    void setDone(String oldName, String newName) throws IOException;
}