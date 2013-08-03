<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
        <title>DMC Latam OpenNLP Demo</title>
    </head>
    <body>
        <h1>DMC Latam OpenNLP Demo</h1>
        
        <h2>Sentence Detector</h2>
        <form name='' method='' action='sentenceDetector'>
        	<textarea name='input' id='input' cols='80' rows='20'></textarea>
        	<br/>
        	<input type='submit' name='Send' />
        </form>
        
        <hr />
        
        <h2>Named Entity Recognition</h2>
        <form name='' method='' action='ner'>
        	<textarea name='input' id='input' cols='80' rows='20'></textarea>
        	<br/>
        	<input type='submit' name='Send' />
        </form>
    </body>
</html>
