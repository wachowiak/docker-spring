var mathModule = (function () {

  var preEqualFunctions = ['add', 'sub', 'mul', 'div']
  var preEqualFunction = null;
  var acc = NaN;
  var curr = NaN;

  var accValue = function () {
    return isNaN(acc) ? 0 : acc;
  }

  var currentValue = function () {
    return isNaN(curr) ? 0 : curr;
  }

  var addDigit = function(digit) {
    curr = isNaN(curr) ? 0 : curr*10;
    curr += parseInt(digit);
  };

  var add = function () {
    acc = isNaN(acc) ? curr : acc += (isNaN(curr) ? 0 : curr);
    curr = NaN;
  }

  var sub = function () {
    acc = isNaN(acc) ? curr : acc -= (isNaN(curr) ? 0 : curr);
    curr = NaN;
  }

  var mul = function () {
    acc = isNaN(acc) ? curr : acc *= (isNaN(curr) ? 1 : curr);
    curr = NaN;
  }

  var div = function () {
    acc = isNaN(acc) || curr == 0 ? curr : acc /= (isNaN(curr) ? 1 : curr);
    curr = NaN;
  }

  var clr = function () {
    acc = NaN;
    curr = NaN;
  }

  var eql = function () {
    if(preEqualFunction != null){
      this[preEqualFunction]();
    }else{
      acc = isNaN(curr) ? acc : curr;
    }
    curr = NaN;
  }

  var lastFunction = function(lastFunction){
    if(preEqualFunctions.indexOf(lastFunction) != -1){
      preEqualFunction = lastFunction;
    }else{
      preEqualFunction = null;
    }
  }

  return {
    add: add,
    sub: sub,
    mul: mul,
    div: div,
    clr: clr,
    eql: eql,
    lastFunction: lastFunction,
    addDigit: addDigit,
    accValue: accValue,
    currentValue: currentValue
  };
})();

function updateDisplay(value){
  $("#display").val(value);
}

function registerButtons(){
  $( ".digit" ).on('click', function() {
    var digit = $(this).text();
    mathModule.addDigit(digit);
    updateDisplay(mathModule.currentValue());
  });

  $( ".fnc" ).on('click', function() {
    var fnc = $(this).attr("math");
    mathModule[fnc]();
    mathModule.lastFunction(fnc);
    updateDisplay(mathModule.accValue());
  });

}

$( document ).ready(function() {
  registerButtons();
});

