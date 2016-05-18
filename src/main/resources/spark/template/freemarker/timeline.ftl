<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Timeline">
<h2>${pageTitle}</h2>
    <#if error??>
    <div class="error">
        <strong>Error:</strong> ${error}
    </div>
    </#if>
    <#if user??>
        <#if profileUser?? && user.id != profileUser.id>
        <div class="followstatus">
            <#if followed>
                <a class="unfollow" href="/t/${profileUser.username}/unfollow">Unfollow user</a>
            <#else>
                <a class="follow" href="/t/${profileUser.username}/follow">Follow user</a>
            </#if>
        </div>
        <#elseif pageTitle != 'Public Calculations Timeline'>
        <div class="twitbox">

            <h3>${user.username} enter an expression for the calculation.</h3>
            <h3 style="padding-bottom: 10px">It supports numbers, operations +, -, *, /, *,% and priorities in the form
                of brackets '(' and ')':</h3>
            <form action="/message" method="post">
                <p><input type="text" name="text" size="60" maxlength="160"><!--
          		--><input type="submit" value="Calculate">
            </form>
        </div>
        </#if>
    </#if>
<ul class="messages">
    <#if messages??>
        <#list messages as message>
        <li><img src="${message.gravatar}">
            <p>
                <strong><a style="float: left" href="/t/${message.username}">${message.username}</a></strong>
            Expression:&nbsp;${message.text} |
            Result:&nbsp;${message.result}

            <a style="float: right">
                <small> ${message.pubDateStr}</small>
            </a>
        <#else>
        <li><em>There're no messages so far.</em>
        </#list>
    <#else>
    <li><em>There're no messages so far.</em>
    </#if>
</ul>
</@layout.masterTemplate>