/**
 * 项目工具类
 *
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2017-09-02
 * @since 1.0
 */
(function ($) {
    $.extend({
        tool: {
            alert: function (content, delayTime, callback) {
                delayTime = delayTime ? "confirm|" + delayTime : "confirm|3000";
                $.alert({
                    title: '友情提示',
                    content: content,
                    confirmButton: '关闭',
                    autoClose: delayTime,
                    confirm: callback
                });
            },
            alertError: function (content, delayTime, callback) {
                delayTime = delayTime ? "confirm|" + delayTime : "confirm|3000";
                $.alert({
                    title: '警告',
                    content: content,
                    confirmButton: '关闭',
                    autoClose: delayTime,
                    confirm: callback
                });
            },
            confirm: function (content, confirmCallback, cancelCallback, delayTime) {
                delayTime = delayTime ? "cancel|" + delayTime : "cancel|5000";
                $.confirm({
                    confirmButtonClass: 'btn-success',
                    cancelButtonClass: 'btn-default',
                    title: '友情提示',
                    content: content,
                    autoClose: delayTime,
                    confirmButton: '确定',
                    cancelButton: '关闭',
                    confirm: confirmCallback,
                    cancel: cancelCallback
                });
            },
            ajaxSuccessConfirm: function (json, callback) {
                if (json.status == 200) {
                    if(json.message){
                        $.tool.confirm(json.message, callback);
                    }
                } else {
                    if(json.message){
                        $.tool.alertError(json.message);
                    }
                }
            },
            ajaxSuccess: function (json) {
                if (json.status == 200) {
                    if(json.message){
                        $.tool.alert(json.message);
                    }
                } else {
                    if(json.message){
                        $.tool.alertError(json.message);
                    }
                }
            },
            ajaxError: function () {
                $.tool.alertError("网络超时！");
            },
            initToastr: function () {
                if(toastr){
                    toastr.options = {
                        closeButton: true,
                        debug: false,
                        progressBar: true,
                        positionClass: "toast-top-right",
                        onclick: null,
                        showDuration: "300",//显示动作时间
                        hideDuration: "1000",//隐藏动作时间
                        timeOut: "2000",//自动关闭超时时间
                        extendedTimeOut: "1000",
                        showEasing: "swing",
                        hideEasing: "linear",
                        showMethod: "fadeIn",
                        hideMethod: "fadeOut"
                    };
                }
            },
            showInfoMessage: function (message) {
                this.initToastr();
                toastr.info(message);
            },
            showSuccessMessage: function (message) {
                this.initToastr();
                toastr.success(message);
            },
            showWarningMessage: function (message) {
                this.initToastr();
                toastr.warning(message);
            },
            showErrorMessage: function (message) {
                this.initToastr();
                toastr.error(message);
            },
            isEmpty: function (value) {
                if (value == null || this.trim(value) == "") {
                    return true;
                }
                return false;
            },
            isInteger: function () {
                return (new RegExp(/^\d+$/).test(this));
            },
            isNumber: function (value, element) {
                return (new RegExp(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/).test(this));
            },
            trim: function (value) {
                if (value == null) {
                    return "";
                }
                return value.replace(/(^\s*)|(\s*$)|\r|\n/g, "");
            },
            html2Txt: function (value) {
                value = this.trim(value);
                value = value.replace(/(\n)/g, "");
                value = value.replace(/(\t)/g, "");
                value = value.replace(/(\r)/g, "");
                value = value.replace(/<\/?[^>]*>/g, "");
                value = value.replace(/\s*/g, "");
                return value;
            },
            currentPath: function () {
                // 域
                var domain = document.domain;
                // 当前页
                var nowurl = document.URL;
                // 来源页
                var fromurl = document.referrer;

                var path = window.location.pathname;
                console.log(domain);
                console.log(fromurl);
                console.log(nowurl);
                return path;
            },
            getMeta: function (name) {
                var meta = document.getElementsByTagName('meta');
                var share_desc = '';
                for (i in meta) {
                    if (typeof meta[i].name != "undefined" && meta[i].name.toLowerCase() == name.toLowerCase()) {
                        share_desc = meta[i].content;
                        break;
                    }
                }
                return share_desc;
            },
            random: function (min, max) {
                return Math.floor((Math.random() * max) + min);
            }
        }
    });
})(jQuery);

/**
 * Created by nicky_chin on 2017-03-19.
 */

/**
 * 时间对象的格式化;
 */
Date.prototype.format = function (format) {
    /*
     * eg:format="YYYY-MM-dd hh:mm:ss";
     */
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
        // millisecond
    };

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
