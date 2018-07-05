/*!
 * 图片剪切 js.
 *
 * @since: 1.0.0
 * @author Way Lau <https://waylau.com>
 */
'use strict';

(function (window, document, cropbox) {

    var isTouchable = 'ontouchstart' in window,
        EVENT_START = isTouchable ? 'touchstart' : 'mousedown',
        EVENT_MOVE = isTouchable ? 'touchmove' : 'mousemove',
        EVENT_END = isTouchable ? 'touchend' : 'mouseup';

    var util = {
        /*
         * @name extend
         * @type function
         * @explain 复制对象
         * */
        extend: function (to, from) {
            var keys = Object.keys(from);
            var i = keys.length;
            while (i--) {
                to[keys[i]] = from[keys[i]];
            }
            return to;
        }
    };

    function Cropper(options) {

        this.defaults = {
            el: '#crop',
            cp: '#cropImg',
            cropBgColor: 'rgba(0, 0, 0, .4)',
            imgSrc: '',
            imageBox: '.crop-wrap-content',
            thumbBox: '.crop-wrap-thum',
            spinner: '.crop-wrap-spinner',
            callback: function () {

            }
        };

        this.defaults = util.extend(this.defaults, options);

        this._init();
    }

    Cropper.prototype = {
        constructor: Cropper,
        _init: function () {
            this.cp = typeof this.defaults.cp == 'string' ? document.querySelector(this.defaults.cp) : this.defaults.cp;
            this.crop = typeof this.defaults.el == 'string' ? document.querySelector(this.defaults.el) : this.defaults.el;
            this.cropMask = this.crop.querySelector('.crop-mask');
            this.cropCroped = this.crop.querySelector('.croped');
            this.cropZoomIn = this.crop.querySelector('.zoomIn');
            this.cropZoomOut = this.crop.querySelector('.zoomOut');
            this.cropMask.style.backgroundColor = this.defaults.cropBgColor;
            this._bindEvent();
        },
        _bind: function (node, event, cb) {
            if (node.attachEvent)
                node.attachEvent('on' + event, cb);
            else if (node.addEventListener)
                node.addEventListener(event, cb, false);
        },
        _bindEvent: function () {
            var self = this;

            this._bind(this.cp, EVENT_START, function () {

                self.cropInput = self.crop.querySelector('.crop-input');

                var aI = self.cropInput.cloneNode();

                self.crop.replaceChild(aI, self.cropInput);

                aI.click();

                self._bind(aI, 'change', function () {
                    self.crop.style.display = 'block';
                    var reader = new FileReader();
                    reader.readAsDataURL(this.files[0]);
                    reader.onload = function (e) {
                        self.defaults.imgSrc = e.target.result;
                        self.cropper = new cropbox(self.defaults);
                    };
                });
            });

            this._bind(this.cropZoomIn, EVENT_START, function () {
                self.cropper.zoomIn();
            });

            this._bind(this.cropZoomOut, EVENT_START, function () {
                self.cropper.zoomOut();
            });

            this._bind(this.cropCroped, EVENT_START, function () {
                self.defaults.callback.call(self, self.cropper.getDataURL(), self.cropper.getBlob());
                self.crop.style.display = 'none';
            });
        }
    };


    if (typeof exports === 'object') module.exports = Cropper;
    else if (typeof define === 'function' && define.amd) define([], function () {
        return Cropper;
    });
    else window.Cropper = Cropper;
})(window, document, function (options) {

    var isTouchable = 'ontouchstart' in window,
        EVENT_START = isTouchable ? 'touchstart' : 'mousedown',
        EVENT_MOVE = isTouchable ? 'touchmove' : 'mousemove',
        EVENT_END = isTouchable ? 'touchend' : 'mouseup';

    var el = document.querySelector(options.imageBox),
        obj = {
            state: {},
            ratio: 1,
            options: options,
            imageBox: el,
            thumbBox: el.querySelector(options.thumbBox),
            spinner: el.querySelector(options.spinner),
            image: new Image(),
            getDataURL: function () {
                var width = this.thumbBox.clientWidth,
                    height = this.thumbBox.clientHeight,
                    canvas = document.createElement("canvas"),
                    dim = el.style.backgroundPosition.split(' '),
                    size = el.style.backgroundSize.split(' '),
                    dx = parseInt(dim[0]) - el.clientWidth / 2 + width / 2,
                    dy = parseInt(dim[1]) - el.clientHeight / 2 + height / 2,
                    dw = parseInt(size[0]),
                    dh = parseInt(size[1]),
                    sh = parseInt(this.image.height),
                    sw = parseInt(this.image.width);

                canvas.width = width;
                canvas.height = height;
                var context = canvas.getContext("2d");
                context.drawImage(this.image, 0, 0, sw, sh, dx, dy, dw, dh);
                return canvas.toDataURL('image/png');
            },
            getBlob: function () {
                var imageData = this.getDataURL();
                var b64 = imageData.replace('data:image/png;base64,', '');
                var binary = atob(b64);
                var array = [];
                for (var i = 0; i < binary.length; i++) {
                    array.push(binary.charCodeAt(i));
                }
                return new Blob([new Uint8Array(array)], {type: 'image/png'});
            },
            zoomIn: function () {
                this.ratio *= 1.1;
                setBackground();
            },
            zoomOut: function () {
                this.ratio *= 0.9;
                setBackground();
            }
        },
        attachEvent = function (node, event, cb) {
            if (node.attachEvent)
                node.attachEvent('on' + event, cb);
            else if (node.addEventListener)
                node.addEventListener(event, cb, false);
        },
        detachEvent = function (node, event, cb) {
            if (node.detachEvent) {
                node.detachEvent('on' + event, cb);
            }
            else if (node.removeEventListener) {
                node.removeEventListener(event, render);
            }
        },
        stopEvent = function (e) {
            if (window.event) e.cancelBubble = true;
            else e.stopImmediatePropagation();
        },
        setBackground = function () {
            var w = parseInt(obj.image.width) * obj.ratio;
            var h = parseInt(obj.image.height) * obj.ratio;

            var pw = (el.clientWidth - w) / 2;
            var ph = (el.clientHeight - h) / 2;

            el.setAttribute('style',
                'background-image: url(' + obj.image.src + '); ' +
                'background-size: ' + w + 'px ' + h + 'px; ' +
                'background-position: ' + pw + 'px ' + ph + 'px; ' +
                'background-repeat: no-repeat');
        },
        imgMouseDown = function (e) {
            stopEvent(e);

            if (isTouchable) {
                e = e.changedTouches[0];
            }

            obj.state.dragable = true;
            obj.state.mouseX = e.clientX;
            obj.state.mouseY = e.clientY;
        },
        imgMouseMove = function (e) {
            stopEvent(e);

            if (isTouchable) {
                e = e.changedTouches[0];
            }

            if (obj.state.dragable) {
                var x = e.clientX - obj.state.mouseX;
                var y = e.clientY - obj.state.mouseY;

                var bg = el.style.backgroundPosition.split(' ');

                var bgX = x + parseInt(bg[0]);
                var bgY = y + parseInt(bg[1]);

                el.style.backgroundPosition = bgX + 'px ' + bgY + 'px';

                obj.state.mouseX = e.clientX;
                obj.state.mouseY = e.clientY;
            }
        },
        imgMouseUp = function (e) {
            stopEvent(e);
            obj.state.dragable = false;
        },
        zoomImage = function (e) {
            var evt = window.event || e;
            var delta = evt.detail ? evt.detail * (-120) : evt.wheelDelta;
            delta > -120 ? obj.ratio *= 1.1 : obj.ratio *= 0.9;
            setBackground();
        };

    obj.spinner.style.display = 'block';
    obj.image.onload = function () {
        obj.spinner.style.display = 'none';
        setBackground();

        attachEvent(el, EVENT_START, imgMouseDown);
        attachEvent(el, EVENT_MOVE, imgMouseMove);
        attachEvent(document.body, EVENT_END, imgMouseUp);
        var mousewheel = (/Firefox/i.test(navigator.userAgent)) ? 'DOMMouseScroll' : 'mousewheel';
        attachEvent(el, mousewheel, zoomImage);
    };
    obj.image.src = options.imgSrc;
    attachEvent(el, 'DOMNodeRemoved', function () {
        detachEvent(document.body, 'DOMNodeRemoved', imgMouseUp)
    });

    return obj;
});







