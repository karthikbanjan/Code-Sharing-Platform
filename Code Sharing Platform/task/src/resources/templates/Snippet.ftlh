<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
    <span id="load_date">${date}</span>
    <br>
    <#if views_restriction??>
    <span id="views_restriction">${views_restriction} more views allowed</span>
    </#if>

    <#if time_restriction??>
        <br>
    <span id="time_restriction">The code will be available for ${time_restriction} seconds</span>
        <br>
    </#if>

    <pre id="code_snippet"><code>${code}</code></pre>
</body>
</html>