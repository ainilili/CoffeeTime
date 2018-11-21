$(function(){
	setTimeout(function(){
		$("#tab_home").addClass('layui-this');	
	},100);

	"use strict";
{
	const txt = 'TOOLSITE';
	const viscosity = 0.005;
	const stiffness = 0.99;
	class Point {
		constructor (i, x, y) {
			this.c = null;
			this.x0 = x;
			this.y0 = y;
			this.x = x + Math.sin(i) * 100;
			this.y = y + Math.cos(i) * 100;
			this.vx = 0.0;
			this.vy = 0.0;
			this.a = 0.0;
			this.s = 0.0;
			this.p0 = this;
			this.p1 = this;
			this.t = 0;
		}
		texture (c, color) {
			this.c = document.createElement("canvas");
			const ctx = this.c.getContext("2d");
			this.c.width = this.c.height = 400;
			ctx.font = "500px Arial Black, Arial";
			ctx.fillStyle = color;
			ctx.textAlign = "center";
			ctx.fillText(c, 200, 385);
		}
		drawSegment() {
			ctx.beginPath();
			ctx.strokeStyle = "#666";
			ctx.moveTo(this.p0.x, this.p0.y);
			ctx.lineTo(this.x, this.y);
			ctx.lineTo(this.p1.x, this.p1.y);
			ctx.stroke();
			ctx.beginPath();
			ctx.arc(this.x, this.y, 3, 0, 2 * Math.PI);
			ctx.stroke();
		}
		draw () {
			const fx = (this.x0 - this.x) * viscosity;
			const fy = (this.y0 - this.y) * viscosity;
			this.vx *= stiffness;
			this.vy *= stiffness;
			this.vx += fx;
			this.vy += fy;
			this.x += this.vx;
			this.y += this.vy;
			if (this.c !== null) {
				ctx.beginPath();
				ctx.save();
				ctx.translate(this.x, this.y);
				const dy = this.p1.x - this.p0.x;
				const dx = this.p1.y - this.p0.y;
				this.a = Math.atan2(dx, dy);
				this.s = Math.sqrt(dy * dy + dx * dx) / 2;
				ctx.rotate(this.a);
				ctx.drawImage(this.c, -this.s * 0.5, -this.s * 0.5, this.s, this.s);
				ctx.restore();
			}
		}
	}
	const points = [];
	const canvas = {
		init() {
			this.elem = document.createElement("canvas");
			this.elem.setAttribute('class','ctext');
			document.getElementById('base-container').appendChild(this.elem);
			this.resize();
			window.addEventListener("resize", () => this.resize(), false);
			return this.elem.getContext("2d");
		},
		resize() {
			this.width = this.elem.width = this.elem.offsetWidth;
			this.height = this.elem.height = this.elem.offsetHeight;
			let x = 0;
			const sx = this.width / (txt.length + 1);
			for (const p of points) {
				p.x0 = x;
				p.y0 = canvas.height / 2;
				x += sx;
			}
		}
	};
	const ctx = canvas.init();
	ctx.imageSmoothingEnabled = true;
	const pointer = {
		x: 0,
		y: 0,
		dx: 0,
		dy: 0,
		drag: null,
		over: null,
		down(e, touch) {
			this.move(e, touch);
			if (this.over) {
				this.dx = this.x - this.over.x;
				this.dy = this.y - this.over.y;
				this.drag = this.over;
				canvas.elem.style.cursor = "move";
			}
		},
		up(e, touch) {
			this.drag = null;
			canvas.elem.style.cursor = "default";
		},
		move(e, touch) {
			const pointer = touch ? e.targetTouches[0] : e;
			this.x = pointer.clientX;
			this.y = pointer.clientY;
			this.over = null;
			if (this.drag === null) {
				for (const p of points) {
					const dx = this.x - p.x;
					const dy = this.y - p.y;
					const d = Math.sqrt(dx * dx + dy * dy);
					if (d < p.s * 0.5) {
						canvas.elem.style.cursor = "pointer";
						this.over = p;
						break;
					} else {
						canvas.elem.style.cursor = "default";
					}
				}
			}
		},
		init() {
			window.addEventListener("mousedown", e => this.down(e, false), false);
			window.addEventListener("touchstart", e => this.down(e, true), false);
			window.addEventListener("mousemove", e => this.move(e, false), false);
			canvas.elem.addEventListener("touchmove", e => this.move(e, true), false);
			window.addEventListener("mouseup", e => this.up(e, false), false);
			window.addEventListener("touchend", e => this.up(e, true), false);
		}
	};
	{
		let x = 0;
		const sx = canvas.width / (txt.length + 1);
		for (let i = 0; i < txt.length + 2; i++) {
			points.push(new Point(i, x, canvas.height / 2));
			x += sx;
		}
		for (let i = 1; i < txt.length + 1; i++) {
			const p = points[i];
			p.p0 = points[i - 1];
			p.p1 = points[i + 1];
			p.s = sx;
			p.texture(txt.charAt(i - 1), i === 5 ? "#f80" : "#333");
		}
	}
	const run = () => {
		requestAnimationFrame(run);
		ctx.clearRect(0, 0, canvas.width, canvas.height);
		for (const p of points) {
			if (pointer.drag === p) {
				p.x = pointer.x - pointer.dx;
				p.y = pointer.y - pointer.dy;
			}
			p.draw();
		}
		for (const p of points) {
			p.drawSegment();
		}
	}
	pointer.init();
	run();	
}
})		




$(function(){
    var cas = document.createElement('canvas');
    cas.setAttribute('id','canvas');
    cas.setAttribute('class','canvas');
    document.body.appendChild(cas);

    'use strict';

    var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

    function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

    function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

    function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

    var mouse, originx, originy, int, cvs;

    /* Safari doesn't support EventTarget :( */
    var EventTarget = EventTarget || false;
    if (EventTarget) {
        EventTarget.prototype.evt = function (event, callback) {
            return this.addEventListener(event, callback);
        };
    } else {
        window.evt = function (event, callback) {
            return this.addEventListener(event, callback);
        };
        document.evt = function (event, callback) {
            return this.addEventListener(event, callback);
        };
        Element.prototype.evt = function (event, callback) {
            return this.addEventListener(event, callback);
        };
    }

    function $(elemId) {
        return document.getElementById(elemId);
    }

    function init() {
        cvs = $("canvas");

        resizeCanvas(cvs);

        window.evt('resize', resizeCanvas, false);
        window.evt("mousemove", function (e) {
            mouse = getMousePos(cvs, e);
            originx = mouse.x;
            originy = mouse.y;
        });
        // window.evt("touchmove", function (e) {
        //     originx = e.originalEvent.touches[0].pageX;
        //     originy = e.originalEvent.touches[0].pageY;
        // });

        var network = new Field(0, 0, 50);
        var emit = new Emitter(0, 0, 50);

        animateCanvas(cvs, function () {
            network.animate();
            emit.animate();
        });
    }

    var Point = function () {
        function Point(x, y, canvas, dia) {
            _classCallCheck(this, Point);

            this.canvas = canvas || cvs;
            this.x = x || 0;
            this.y = y || 0;
            this.vx = 0;
            this.vy = 0;
            this.speed = Math.random() * .5 + .2;
            this.angle = Math.random() * 360;
            this.diaSet = dia || 2 + Math.random() * 10;
            this.dia = this.diaSet;
            this.age = 0;
            var hue = Math.floor(Math.random() * 360);
            this.fill = 'hsl(' + hue + ', 95%, 70%)';
            this.line = Math.random() > .5 ? true : false;
        }

        _createClass(Point, [{
            key: 'emit',
            value: function emit(life) {
                var s = this.speed * 2;
                this.angle += Math.random() * 10 - 5;
                this.x += s * Math.cos(this.angle * Math.PI / 180);
                this.y += s * Math.sin(this.angle * Math.PI / 180);
                this.age += 1 / life;
                this.boundary();
            }
        }, {
            key: 'boundary',
            value: function boundary() {
                if (this.x < 0) {
                    this.x = this.canvas.width;
                }
                if (this.x > this.canvas.width) {
                    this.x = 0;
                }
                if (this.y < 0) {
                    this.y = this.canvas.height;
                }
                if (this.y > this.canvas.height) {
                    this.y = 0;
                }
            }
        }, {
            key: 'field',
            value: function field(life) {
                var s = this.speed;
                this.angle += Math.random() * 10 - 5;
                this.x += s * Math.cos(this.angle * Math.PI / 180);
                this.y += s * Math.sin(this.angle * Math.PI / 180);
                this.age += 1 / life;
                this.boundary();
            }
        }, {
            key: 'shrink',
            value: function shrink(life) {
                this.dia = (1 - this.age) * this.diaSet;
            }
        }, {
            key: 'draw',
            value: function draw() {
                var ctx = this.canvas.getContext('2d'),
                    x = this.x,
                    y = this.y,
                    dia = this.dia,
                    age = this.age;

                ctx.beginPath();
                ctx.fillStyle = this.fill;
                ctx.strokeStyle = this.fill;
                ctx.lineWidth = 2;
                ctx.arc(x, y, dia, 0, 2 * Math.PI);
                ctx.closePath();

                this.line !== true ? ctx.fill() : ctx.stroke();
            }
        }]);

        return Point;
    }();

    var Particle = function () {
        function Particle() {
            _classCallCheck(this, Particle);
        }

        _createClass(Particle, [{
            key: 'setPosition',
            value: function setPosition(x, y) {
                this.x = x;
                this.y = y;
            }
        }, {
            key: 'getPosition',
            value: function getPosition(x, y) {
                return {
                    x: this.x,
                    y: this.y
                };
            }
        }, {
            key: 'spawn',
            value: function spawn(x, y, amount, dia) {

                var arr = [];
                dia = dia || false;

                amount = amount || 1;

                if (amount > 1) {
                    for (var i = 0; i < amount; i++) {
                        if (dia) {
                            arr.push(new Point(x, y, cvs, dia));
                        } else {
                            arr.push(new Point(x, y, cvs));
                        }
                    }
                } else {
                    arr = new Point(x, y, cvs, dia);
                }

                return arr;
            }
        }]);

        return Particle;
    }();

    // Particle Emitter


    var Emitter = function (_Particle) {
        _inherits(Emitter, _Particle);

        function Emitter(x, y, life, mouse, dia) {
            _classCallCheck(this, Emitter);

            var _this = _possibleConstructorReturn(this, (Emitter.__proto__ || Object.getPrototypeOf(Emitter)).call(this));

            if (mouse === undefined) {
                _this.mouse = true;
            } else {
                _this.mouse = mouse;
            }

            _this.particles = [];
            _this.x = x || 0;
            _this.y = y || 0;
            _this.life = life || 20;
            _this.canvas = cvs;
            _this.dia = dia || false;
            return _this;
        }

        _createClass(Emitter, [{
            key: 'animate',
            value: function animate() {
                var particles = this.particles;
                if (this.mouse) {
                    this.setPosition(originx, originy);
                }

                var mul = 1;

                for (var i = 0; i < mul; i++) {
                    particles.push(this.spawn(this.x, this.y, 1));
                }

                if (particles.length > this.life * mul) {
                    for (var _i = 0; _i < mul; _i++) {
                        particles.shift();
                    }
                }

                this.render(this.canvas);
            }
        }, {
            key: 'render',
            value: function render() {
                var life = this.life;
                var ctx = this.canvas.getContext('2d');
                var particles = this.particles;

                for (var i = 0; i < particles.length; i++) {
                    var p = particles[i];
                    p.draw();
                    p.emit(this.life);
                    p.shrink();
                }
            }
        }]);

        return Emitter;
    }(Particle);

    // Particle Field


    var Field = function (_Particle2) {
        _inherits(Field, _Particle2);

        function Field(x, y, life) {
            _classCallCheck(this, Field);

            var _this2 = _possibleConstructorReturn(this, (Field.__proto__ || Object.getPrototypeOf(Field)).call(this));

            _this2.particles = [];
            _this2.canvas = cvs;
            _this2.x = x || 0;
            _this2.y = y || 0;
            _this2.life = life;

            for (var i = 0; i < _this2.life; i++) {
                var _x = Math.random() * cvs.width,
                    _y = Math.random() * cvs.height;

                _this2.particles.push(_this2.spawn(_x, _y, 1));
            }
            return _this2;
        }

        _createClass(Field, [{
            key: 'animate',
            value: function animate() {
                this.render(canvas);
            }
        }, {
            key: 'render',
            value: function render(canvas) {
                var ctx = this.canvas.getContext('2d');
                var particles = this.particles;

                for (var i = 0; i < particles.length; i++) {
                    var p = particles[i];
                    p.draw();
                    p.field(this.life);
                }
            }
        }]);

        return Field;
    }(Particle);

    function getMousePos(cvs, evt) {
        var rect = cvs.getBoundingClientRect();
        return {
            x: evt.clientX - rect.left,
            y: evt.clientY - rect.top
        };
    }

    //
    function animateCanvas(canvas, callback) {

        var ctx = canvas.getContext('2d');
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        callback();

        requestAnimationFrame(animateCanvas.bind(null, canvas, callback));
    }

    //Update canvas size to fill window
    function resizeCanvas(canvas) {
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
        originx = canvas.width / 2;
        originy = canvas.height / 2;
    }

    init();
})

