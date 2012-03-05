/* Copyright (c) 2012, University of Edinburgh.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice, this
 *   list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 * * Neither the name of the University of Edinburgh nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * This software is derived from (and contains code from) QTItools and MathAssessEngine.
 * QTItools is (c) 2008, University of Southampton.
 * MathAssessEngine is (c) 2010, University of Edinburgh.
 */
package org.qtitools.qti.node.expression.operator;

import uk.ac.ed.ph.jqtiplus.value.BooleanValue;
import uk.ac.ed.ph.jqtiplus.value.DirectedPairValue;
import uk.ac.ed.ph.jqtiplus.value.DurationValue;
import uk.ac.ed.ph.jqtiplus.value.FileValue;
import uk.ac.ed.ph.jqtiplus.value.FloatValue;
import uk.ac.ed.ph.jqtiplus.value.IdentifierValue;
import uk.ac.ed.ph.jqtiplus.value.IntegerValue;
import uk.ac.ed.ph.jqtiplus.value.NullValue;
import uk.ac.ed.ph.jqtiplus.value.OrderedValue;
import uk.ac.ed.ph.jqtiplus.value.PairValue;
import uk.ac.ed.ph.jqtiplus.value.PointValue;
import uk.ac.ed.ph.jqtiplus.value.StringValue;
import uk.ac.ed.ph.jqtiplus.value.UriValue;
import uk.ac.ed.ph.jqtiplus.value.Value;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.qtitools.qti.node.expression.ExpressionAcceptTest;

/**
 * Test of <code>Ordered</code> expression.
 * 
 * @see uk.ac.ed.ph.jqtiplus.node.expression.operator.Ordered
 */
@RunWith(Parameterized.class)
public class OrderedAcceptTest extends ExpressionAcceptTest {

    private static final OrderedValue ORDERED_1__1_2_3;

    private static final OrderedValue ORDERED_2__1_2_3_4_5_6_7;

    static {
        // ORDERED_1__1_2_3
        ORDERED_1__1_2_3 = new OrderedValue();
        ORDERED_1__1_2_3.add(new IntegerValue(1));
        ORDERED_1__1_2_3.add(new IntegerValue(2));
        ORDERED_1__1_2_3.add(new IntegerValue(3));
        // ORDERED_2__1_2_3_4_5_6_7
        ORDERED_2__1_2_3_4_5_6_7 = new OrderedValue();
        ORDERED_2__1_2_3_4_5_6_7.add(new IntegerValue(1));
        ORDERED_2__1_2_3_4_5_6_7.add(new IntegerValue(2));
        ORDERED_2__1_2_3_4_5_6_7.add(new IntegerValue(3));
        ORDERED_2__1_2_3_4_5_6_7.add(new IntegerValue(4));
        ORDERED_2__1_2_3_4_5_6_7.add(new IntegerValue(5));
        ORDERED_2__1_2_3_4_5_6_7.add(new IntegerValue(6));
        ORDERED_2__1_2_3_4_5_6_7.add(new IntegerValue(7));
    }

    /**
     * Creates test data for this test.
     * 
     * @return test data for this test
     */
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // null
                { "<ordered>" +
                        "</ordered>", NullValue.INSTANCE }, { "<ordered>" +
                        "<null/>" +
                        "</ordered>", NullValue.INSTANCE }, { "<ordered>" +
                        "</ordered>", new OrderedValue() }, { "<ordered>" +
                        "<null/>" +
                        "</ordered>", new OrderedValue() },
                // identifier
                { "<ordered>" +
                        "<baseValue baseType='identifier'>identifier</baseValue>" +
                        "</ordered>", new OrderedValue(new IdentifierValue("identifier")) },
                // boolean
                { "<ordered>" +
                        "<baseValue baseType='boolean'>true</baseValue>" +
                        "</ordered>", new OrderedValue(BooleanValue.TRUE) },
                // integer
                { "<ordered>" +
                        "<baseValue baseType='integer'>1</baseValue>" +
                        "</ordered>", new OrderedValue(new IntegerValue(1)) }, { "<ordered>" +
                        "<baseValue baseType='integer'>1</baseValue>" +
                        "<baseValue baseType='integer'>2</baseValue>" +
                        "<baseValue baseType='integer'>3</baseValue>" +
                        "</ordered>", ORDERED_1__1_2_3 }, { "<ordered>" +
                        "<baseValue baseType='integer'>1</baseValue>" +
                        "<null/>" +
                        "<baseValue baseType='integer'>2</baseValue>" +
                        "<null/>" +
                        "<baseValue baseType='integer'>3</baseValue>" +
                        "</ordered>", ORDERED_1__1_2_3 }, { "<ordered>" +
                        "<baseValue baseType='integer'>1</baseValue>" +
                        "<baseValue baseType='integer'>2</baseValue>" +
                        "<ordered>" +
                        "<baseValue baseType='integer'>3</baseValue>" +
                        "<baseValue baseType='integer'>4</baseValue>" +
                        "<baseValue baseType='integer'>5</baseValue>" +
                        "</ordered>" +
                        "<baseValue baseType='integer'>6</baseValue>" +
                        "<baseValue baseType='integer'>7</baseValue>" +
                        "</ordered>", ORDERED_2__1_2_3_4_5_6_7 },
                // float
                { "<ordered>" +
                        "<baseValue baseType='float'>1</baseValue>" +
                        "</ordered>", new OrderedValue(new FloatValue(1)) },
                // string
                { "<ordered>" +
                        "<baseValue baseType='string'>string</baseValue>" +
                        "</ordered>", new OrderedValue(new StringValue("string")) },
                // point
                { "<ordered>" +
                        "<baseValue baseType='point'>1 1</baseValue>" +
                        "</ordered>", new OrderedValue(new PointValue(1, 1)) },
                // pair
                { "<ordered>" +
                        "<baseValue baseType='pair'>identifier_1 identifier_2</baseValue>" +
                        "</ordered>", new OrderedValue(new PairValue("identifier_1", "identifier_2")) },
                // directedPair
                { "<ordered>" +
                        "<baseValue baseType='directedPair'>identifier_1 identifier_2</baseValue>" +
                        "</ordered>", new OrderedValue(new DirectedPairValue("identifier_1", "identifier_2")) },
                // duration
                { "<ordered>" +
                        "<baseValue baseType='duration'>1</baseValue>" +
                        "</ordered>", new OrderedValue(new DurationValue(1)) },
                // file
                { "<ordered>" +
                        "<baseValue baseType='file'>file</baseValue>" +
                        "</ordered>", new OrderedValue(new FileValue("file")) },
                // uri
                { "<ordered>" +
                        "<baseValue baseType='uri'>uri</baseValue>" +
                        "</ordered>", new OrderedValue(new UriValue("uri")) },
                // ordered
                { "<ordered>" +
                        "<ordered>" +
                        "<baseValue baseType='integer'>1</baseValue>" +
                        "</ordered>" +
                        "</ordered>", new OrderedValue(new IntegerValue(1)) },
        });
    }

    /**
     * Constructs <code>Ordered</code> expression test.
     * 
     * @param xml xml data used for creation tested expression
     * @param expectedValue expected evaluated value
     */
    public OrderedAcceptTest(String xml, Value expectedValue) {
        super(xml, expectedValue);
    }
}