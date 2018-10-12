/**
 * Created by rob on 2/13/17.
 */
var canvas;
var ctx;
var fSize;
var f2Size;
var fWLocation;
var fHLocation;
var pageName;
var leftBorder = 0;
var topBorder = 0;
var width = 300;
var height = 75;


function init(label) {
    pageName = label;
    canvas = document.getElementById('Canvas');
    if (canvas.getContext) {
        ctx = canvas.getContext("2d");
        window.addEventListener('resize', resizeCanvas, false);
        window.addEventListener('orientationchange', resizeCanvas, false);
        resizeCanvas();
    }
    drawText();
}
function resizeCanvas() {
    canvas.width = width;
    canvas.height = height;
    fSize = canvas.height * .40;
    f2Size = (fSize * .50);
    fWLocation = leftBorder + width * .275;
    fHLocation = topBorder + fSize;
    drawText();
}

function drawText() {
    if (canvas.getContext) {
        ctx = canvas.getContext("2d");
        ctx.font = fSize + "pt verdana";
        ctx.fillStyle = "#000000";
        ctx.fillText("MaxBit", fWLocation, fHLocation);
        ctx.font = f2Size + "pt verdana";
        ctx.fillStyle = "#aaaaaa";
        ctx.fillText("technology", fWLocation + (f2Size * 1.5), fHLocation + (f2Size * .925));
    }
}
