<#macro out title>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/sidebars.css" rel="stylesheet">
    <link href="/css/headers.css" rel="stylesheet">
    <link href="/css/bootstrap-fileinput.css" rel="stylesheet">
    <script src="/js/bootstrap-fileinput.js"></script>
    <script src="/js/jquery-3.6.0.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/layui/layui.all.js"></script>
    <link rel="icon" type="image/x-icon" href="/images/favicon.icon">
    <style type="text/css">
        [v-cloak] {
            display: none !important;
        }
    </style>

</head>
<body>
<div class="container" style="max-width: 960px;">

    <#include "/inclusion/header.ftl"/>
    <#nested >

</div>

<script>

    $(function () {

        layui.config({
            version: false
            , debug: false
            , base: ''
        });
    });
</script>
</body>
</html>
</#macro>