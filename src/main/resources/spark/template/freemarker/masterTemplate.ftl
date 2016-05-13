<#macro masterTemplate title="Welcome">
    <!DOCTYPE html
            PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
            "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <title>${title} | Calculator</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
    </head>
    <body>
		<div class="page">
	  		<h1>Extended String Calculator</h1>
		  	<div class="navigation">
		  	<#if user??>
		    	<a href="/">My calculations timeline</a>
		    	<a href="/public">Public calculations timeline</a>
		    	<a  style="float:right" href="/logout">sign out [${user.username}]</a>
		  	<#else>
			    <a href="/public">Public calculations timeline</a>
			    <a  style="float:right" href="/register">Register</a>
			    <a  style="float:right" href="/login">Login</a>

		  	</#if>
		  	</div>
		  	<div class="body">
		  		<#nested />
		  	</div>
		  	<div class="footer">
			    Extended String Calculator &mdash; Created by Roman Savinyuk 2016 - <a href="https://www.facebook.com/roman.savinuik" target="_blank">Facebook</a> | <a href="https://www.linkedin.com/in/roman-savinuik-18429739" target="_blank">LinkedIN</a>
		  	</div>
		</div>
    </body>
    </html>
</#macro>