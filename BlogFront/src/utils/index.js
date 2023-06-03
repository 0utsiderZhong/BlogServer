import AES from 'crypto-js/aes';
import {LineBreakMode} from "@/utils/const";
import ENCUTF8 from 'crypto-js/enc-utf8';

/**
 * LocalStorage的操作
 * classify 分类
 * key 存储键
 * value 存储值
 * */
export function saveToLocal(classify, key, value) {
    try {
        let blog = window.localStorage.__blog__;
        if (!blog) {
            blog = {};
            blog[classify] = {};
        } else {
            blog = JSON.parse(AES.decrypt(blog, 'local storage').toString(ENCUTF8));
            if (!blog[classify]) {
                blog[classify] = {};
            }
        }
        blog[classify][key] = value;
        window.localStorage.__blog__ = AES.encrypt(JSON.stringify(blog), 'local storage').toString();
    } catch (exception) {
        window.console.log(exception);
    }
}

// scrollTop animation
export function scrollTop(el, from = 0, to, duration = 500) {
    if (!window.requestAnimationFrame) {
        window.requestAnimationFrame = (
            window.webkitRequestAnimationFrame ||
            window.mozRequestAnimationFrame ||
            window.msRequestAnimationFrame ||
            function (callback) {
                return window.setTimeout(callback, 1000 / 60);
            }
        );
    }
    const difference = Math.abs(from - to);
    const step = Math.ceil(difference / duration * 50);

    function scroll(start, end, step) {
        if (start === end) return;
        let d = (start + step > end) ? end : start + step;
        if (start > end) {
            d = (start - step < end) ? end : start - step;
        }
        if (el === window) {
            window.scrollTo(d, d);
        } else {
            el.scrollTop = d;
        }
        window.requestAnimationFrame(() => scroll(d, end, step));
    }

    scroll(from, to, step);
}

export const mixin = {
    methods: {
        // 滚动到评论区域
        scrollToComments(selector) {
            window.console.log(selector);
            try {
                let commentEle = document.querySelector(selector);
                const sTop = document.documentElement.scrollTop || document.body.scrollTop;
                const commentEleSTop = commentEle.offsetTop;
                scrollTop(window, sTop, commentEleSTop, Math.max((commentEleSTop - sTop) / 10, 1000));
            } catch (error) {
                window.console.log(error);
            }
        }
    },

    filters: {
        // 用于格式化时间的过滤器
        // 用于处理行尾省略号的过滤器
        textLineBreak: function (text, maxLength, lineBreakMode) {
            if (text === undefined || text === null || text.length === 0) {
                return '';
            }
            if (lineBreakMode === null || lineBreakMode === undefined) {
                lineBreakMode = LineBreakMode.EllipsisTruncatingTail;
            }
            switch (lineBreakMode) {
                case LineBreakMode.WrappingTruncatingTail:
                    return text.substr(0, maxLength);
                case LineBreakMode.WrappingTruncatingHead:
                    return text.substr(-maxLength);
                case LineBreakMode.EllipsisTruncatingTail:
                    return text.substr(0, maxLength) + (text.length > maxLength ? '...' : '');
                case LineBreakMode.EllipsisTruncatingMiddle:
                    var resultText = text.substr(0, maxLength);
                    if (text.length > maxLength) {
                        return resultText.substr(0, parseInt(maxLength / 2)) + '...' + resultText.substr(parseInt(maxLength / 2));
                    }
                    return resultText;
                case LineBreakMode.EllipsisTruncatingHead:
                    return (text.length > maxLength ? '...' : '') + text.substr(-maxLength);
            }
            return text;
        }
    }
};



