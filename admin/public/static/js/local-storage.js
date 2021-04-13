//localStorage：本地缓存，浏览器关闭后，下次再打开还能读到
//自带的setItem只能操作字符串，但我们经常要放JSON对象，所以封装一下

LOCAL_KEY_REMEMBER_USER = "LOCAL_KEY_REMEMBER_USER"; // 记住我

LocalStorage = {
    get: function (key) {
        let v = localStorage.getItem(key);
        if (v && typeof(v) !== "undefined" && v !== "undefined") {
            return JSON.parse(v);
        }
    },
    set: function (key, data) {
        localStorage.setItem(key, JSON.stringify(data));
    },
    remove: function (key) {
        localStorage.removeItem(key);
    },
    clearAll: function () {
        localStorage.clear();
    }
};
