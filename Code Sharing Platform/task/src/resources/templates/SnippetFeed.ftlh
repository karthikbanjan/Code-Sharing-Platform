<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <meta charset="UTF-8">
    <title>Latest</title>
</head>
<body>
    <#list codeSnippets as codeSnippet>
        <span id="load_date">${codeSnippet.getStringDate()}</span>
        <pre id="code_snippet"><code>${codeSnippet.code}</code></pre>
    </#list>
</body>
</html>