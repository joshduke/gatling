/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gatling.http.request.builder

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mock.Mockito
import com.ning.http.client.SignatureCalculator
import java.net.URI
import io.gatling.core.validation.Success
import io.gatling.http.config.HttpProtocol

@RunWith(classOf[JUnitRunner])
class HttpRequestBuilderSpec extends Specification with Mockito {

  def mockComonAttributes() = CommonAttributes(_ => Success("attributes"), "method", Right(new URI("http://gatling-tool.org")))

  "request builder" should {
    "set signature calculator" in {
      var builder = new HttpRequestBuilder(mockComonAttributes(), HttpAttributes())
      val sigCalc = mock[SignatureCalculator]
      builder = builder.signatureCalculator(sigCalc)

      val httpRequest = builder.build(HttpProtocol.default, false)
      httpRequest.signatureCalculator.get must_== sigCalc
    }
  }
}
