<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="#" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <img src="/images/mainlogo.png" alt="dailyhub" height="38px">
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0"
                style="margin: 0 auto;">
                <li><a href="/menu/${Session["userentity"].openid}" class="nav-link px-2 link-secondary ">我的收藏</a></li>
                <li><a href="/sqare" class="nav-link px-2 link-secondary">收藏广场</a></li>
                <li><a href="https://wql.luoqin.ltd/" class="nav-link px-2 link-secondary">个人网站</a></li>
            </ul>

            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" action="/search" method="get">
                <input type="hidden" name="userId" value="${userId}">
                <input name="q" id="keyword" type="search" class="form-control" placeholder="搜索..."
                       aria-label="Search" value="${q}">
            </form>


        <#if Session["userentity"]??>
            <div class="dropdown text-end">
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="${Session["userentity"].avater}"
                         alt="mdo" width="32" height="32" class="rounded-circle">
                    <span>${Session["userentity"].username}</span>
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                    <li><a class="dropdown-item" href="/collect/edit">新建收藏</a></li>
                    <li><a class="dropdown-item" href="/persopnal">个人中心</a></li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li><a class="dropdown-item" href="/logout">注销</a></li>
                </ul>
            </div>
            <!--已登录-->
            <#else>
                <!--未登录-->
                <a class="btn btn-primary" href="/login" role="button">登录</a>
        </#if>
        </div>
    </div>




</header>
