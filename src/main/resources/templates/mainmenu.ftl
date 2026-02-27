<#import "/inclusion/out.ftl" as Out/>
<@Out.out "空想家Smile">

    <div id="app" class="row justify-content-md-center">

        <!--侧边日期-->
        <div class="col col-3">
            <div class="flex-shrink-0 p-3 bg-white" style="width: 280px;">
                <ul class="list-unstyled ps-0">
                    <#list dateline as dateline>
                        <li class="mb-1">
                            <button class="dateline btn btn-toggle align-items-center rounded collapsed"
                                    data-bs-toggle="collapse"
                                    data-bs-target="#collapse-${dateline.title}" aria-expanded="true">
                                ${dateline.title}
                            </button>
                            <div class="collapse show" id="collapse-${dateline.title}">
                                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                                    <#list dateline.collectAtoes as child>
                                        <li><a href="javascript:handleDateline('${child.title}')" class="link-dark rounded">${child.title}</a></li>
                                    </#list>
                                </ul>
                            </div>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>


        <!---->
        <div class="col col-9" id="collects-col">
            <#include '/inclusion/collect-tpl.ftl'>

            <div class="row" id="masonry"></div>

        </div>

    <script>

        var laytpl, flow
        layui.use(['laytpl', 'flow'], function () {
            laytpl = layui.laytpl;
            flow = layui.flow;
        })

        function flowload(dateline) {
            var userId;

            <#if Session["userid"]??>
                userId = '${Session["userid"]}'
                <#else>
                    userId = '${Session["userentity"].id}'
             </#if>
            flow.load({
                elem: '#masonry'
                , isAuto: false
                , end: '到底了~'
                , done: function (page, next) {
                    $.get('/collectflow/' + userId + '/' + dateline, {
                        page: page,
                        size: 10
                    }, function (res) {

                        var lis = []
                        var gettpl = $('#collect-card-tpl').html();
                        laytpl(gettpl).render(res.data, function (html) {
                            // $("#masonry").append(html);
                            $(".layui-flow-more").before(html);

                        })
                        next(lis.join(''), page < res.tolalPages);
                    })
                }
            })
        }

        function handleDateline(dateline) {
            $("#masonry").html('')
            flowload(dateline)
        };
        $(function () {
            flowload("All")
        });

        function handleDel(id) {
            layer.confirm('是否确认删除', function (index) {
                $.get('/collect/delete?id=' + id, function (res) {
                    if (res.code == 200) {
                        $('#masonry-item-' + id).remove()
                    }
                    layer.msg("删除成功！")
                } )
            })
        }

    </script>
    </div>
</@Out.out>