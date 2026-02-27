<#import "/inclusion/out.ftl" as Layout>

<@Layout.out "我的收藏">

    <div id="app" class="row justify-content-md-center">

        <#if searchTip><div class="alert alert-info" role="alert">${searchTip}</div></#if>

        <!---->
        <div class="col" id="collects-col">

            <#include '/inclusion/collect-tpl.ftl'>
            <div class="row" id="masonry"></div>

        </div>

        <script>



            var laytpl, flow
            layui.use(['laytpl', 'flow'], function () {
                laytpl = layui.laytpl;
                flow = layui.flow;
            })

            function flowload() {
                var userId = '${Session["userentity"].id}'
                flow.load({
                    elem: '#masonry'
                    , isAuto: false
                    , end: '到底了~'
                    , done: function (page, next) {
                        $.get('/collects/square', {
                            page: page,
                            size: 10,
                            q: '${q}',
                            userId: userId
                        }, function (res) {

                            var lis = []
                            var gettpl = $('#collect-card-tpl').html();
                            laytpl(gettpl).render(res.data, function (html) {
                                $(".layui-flow-more").before(html);
                            })
                            next(lis.join(''), page < res.tolalPages);
                        })
                    }
                })
            }

            function handleDel(id) {
                layer.confirm('是否确认删除', function (index) {
                    $.post('/api/collect/delete?id=' + id, function (res) {
                        if (res.code == 200) {
                            $('#masonry-item-' + id).remove()
                        }
                        layer.msg("删除成功！")
                    } )
                })
            }

            $(function () {
                flowload("all")
            });

        </script>

    </div>

</@Layout.out >