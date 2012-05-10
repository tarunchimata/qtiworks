<%--

This fragment formats the result of schema validation

Copyright (c) 2012, The University of Edinburgh.
All Rights Reserved

--%>
<%@ tag body-content="empty" %>
<%@ taglib prefix="utils" uri="http://www.ph.ed.ac.uk/utils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="validator" tagdir="/WEB-INF/tags/validator" %>
<%@ attribute name="rootObjectLookup" required="true" type="uk.ac.ed.ph.jqtiplus.resolution.RootObjectLookup" %>

<c:set var="qtiXmlObjectReadResult" value="${rootObjectLookup.rootObjectHolder}"/>
<c:set var="qtiXmlInterpretationException" value="${rootObjectLookup.badResourceException}"/>
<c:set var="xmlParseResult" value="${qtiXmlObjectReadResult!=null ? qtiXmlObjectReadResult.xmlParseResult : (qtiXmlInterpretationException!=null ? qtiXmlInterpretationException.xmlParseResult : null)}"/>
<c:choose>
  <c:when test="${xmlParseResult==null || !xmlParseResult.parsed}">
    <%-- Did not get this far --%>
  </c:when>
  <c:when test="${xmlParseResult.schemaValid}">
    <div class="resultPanel success">
      <h3>Schema validation success</h3>
      <div class="details">
        <p>
          The QTI XML was successfully validated against our golden copies of
          the following supported schemas:
        </p>
        <c:if test="${!empty xmlParseResult.supportedSchemaNamespaces}">
          <ul>
            <c:forEach var="ns" items="${xmlParseResult.supportedSchemaNamespaces}">
              <li><c:out value="${ns}"/></li>
            </c:forEach>
          </ul>
        </c:if>
      </div>
    </div>
  </c:when>
  <c:otherwise>
    <div class="resultPanel failure">
      <h3>Schema validation failed</h3>
      <div class="details">
        <c:choose>
          <c:when test="${!empty xmlParseResult.unsupportedSchemaNamespaces}">
            <p>The XML declared the following schema namespaces that are not supported by this system:</p>
            <ul>
              <c:forEach var="ns" items="${xmlParseResult.unsupportedSchemaNamespaces}">
                <li><c:out value="${ns}"/></li>
              </c:forEach>
            </ul>
            <p>
              Schema validation was therefore not performed in this case.
            </p>
          </c:when>
          <c:otherwise>
            <p>
              The QTI XML was not successfully validated against our golden copies of
              the following supported schemas:
            </p>
            <c:if test="${!empty xmlParseResult.supportedSchemaNamespaces}">
              <ul>
                <c:forEach var="ns" items="${xmlParseResult.supportedSchemaNamespaces}">
                  <li><c:out value="${ns}"/></li>
                </c:forEach>
              </ul>
            </c:if>
            <p>
              Errors are listed below:
            </p>
            <validator:xmlParseErrors xmlParseResult="${xmlParseResult}"/>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </c:otherwise>
</c:choose>