/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2019 Tomasz Linkowski.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
  // https://github.com/tlinkowski/tlinkowski-superpom
  id("pl.tlinkowski.gradle.my.superpom")
}

config {
  info {
    name = "NeatJ"
    description = "Neat utilities for Java."
    tags = listOf("java", "utility")
    inceptionYear = "2018"

    links {
      website = "https://github.com/tlinkowski/NeatJ"
      issueTracker = "$website/issues"
      scm = "$website.git"
    }
  }
}

subprojects {
  // https://docs.gradle.org/current/userguide/java_library_plugin.html
  apply<JavaLibraryPlugin>()

  //region CONFIGURATION PROPERTIES
  val api by configurations
  val implementation by configurations
  val compileOnly by configurations
  //endregion

  dependencies {
    val basicAnnotationsVersion: String by project // https://github.com/tlinkowski/basic-annotations

    compileOnly(group = "pl.tlinkowski.annotation", name = "pl.tlinkowski.annotation.basic", version = basicAnnotationsVersion)
  }

  config {
    bintray.enabled = true

    javadoc.autoLinks {
      configurations = listOf("api", "implementation", "compileOnly")
    }
  }
}
