const themeJson = {
    "light": {
        "base": "#f8f5f8",
        "base_reverse": "#212529",
        "base_more": "#fff",
        "base_reverse_more": "#000",
        "base_less": "#505050",
        "base_reverse_less": "#a0a0a0",
        "body_bg": "#f2f3f5"
    }, "dark": {
        "base": "#212529",
        "base_reverse": "#f8f5f8",
        "base_more": "#000",
        "base_reverse_more": "#fff",
        "base_less": "#a0a0a0",
        "base_reverse_less": "#505050",
        "body_bg": "#323232"
    }
};
var theme = "light";
function changeThemeCss(data) {
    document.documentElement.style.setProperty("--base", data.base);
    document.documentElement.style.setProperty("--base-reverse", data.base_reverse);
    document.documentElement.style.setProperty("--base-more", data.base_more);
    document.documentElement.style.setProperty("--base-reverse-more", data.base_reverse_more);
    document.documentElement.style.setProperty("--base-less", data.base_less);
    document.documentElement.style.setProperty("--base-reverse-less", data.base_reverse_less);
    document.documentElement.style.setProperty("--body-bg", data.body_bg);
}

function ChangeTheme() {
    theme = (theme == "light") ? "dark" : "light";
    if (theme == "light") {
        changeThemeCss(themeJson.light);
    } else {
        changeThemeCss(themeJson.dark);
    }
    setCookie('theme',theme);
}

function setCookie(name,value)
{
    document.cookie = name + "="+ escape (value);
}
function getCookie(name)
{
    let arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
function clearCookie(){
    var keys=document.cookie.match(/[^ =;]+(?=\=)/g);
    if (keys) {
        for (var i = keys.length; i--;)
            document.cookie=keys[i]+'=0;expires=' + new Date( 0).toUTCString()
    }
}