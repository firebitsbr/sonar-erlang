/*
 * Sonar Erlang Plugin
 * Copyright (C) 2012 Tamas Kende
 * kende.tamas@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.erlang.parser;

import com.google.common.base.Joiner;
import com.sonar.sslr.api.Rule;
import org.junit.Test;
import org.sonar.erlang.api.ErlangGrammar;

import static org.sonar.sslr.tests.Assertions.assertThat;

public class ErlangParserFunctionCallExpressionTest {
    ErlangGrammar g = new ErlangGrammarImpl();
    Rule p = g.statement;

  @Test
  public void functionCallExpressions() {
    assertThat(p).matches((code("method(\"hello\")")));
    assertThat(p).matches((code("method(12)")));
    assertThat(p).matches((code("method(\"hello\",234234)")));
    assertThat(p).matches((code("haho:method(\"hello\")")));
    assertThat(p).matches((code("method(\"hello\")")));
    assertThat(p).matches((code("io:format(\"assert error in module ~p on line ~p~n\")")));
    assertThat(p).matches(
        (code("string:strip(erlang:system_info(system_architecture),right,$\n)")));
  }

  private static String code(String... lines) {
    return Joiner.on("\n").join(lines);
  }

}
