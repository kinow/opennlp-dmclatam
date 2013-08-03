<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
        <title>DMC Latam OpenNLP Demo</title>
    </head>
    <body>
        <h1>DMC Latam OpenNLP Demo</h1>
        
        <p>NLP results</p>
        
        <h2>Input</h2>
        <pre><c:out value="${input}" /></pre>
        
        <h2>Output</h2>
        <pre><c:out value="${output}" /></pre>
    </body>
</html>
