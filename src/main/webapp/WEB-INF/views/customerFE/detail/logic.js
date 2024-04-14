function formatNumber(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

const bigImg = document.querySelector(".product-content-big-img img")
const smallImg = document.querySelectorAll(".product-content-small-img img")
smallImg.forEach(function(imgItem, X){
    imgItem.addEventListener("click", function(){
        bigImg.src = imgItem.src
    })
})

document.addEventListener('DOMContentLoaded', function() {
    var inputField = document.getElementById('inputField');
    var inputField2 = document.getElementById('inputField2');
    var inputField1 = document.getElementById('inputField1');
    var addButton = document.getElementById('addButton');
    var subtractButton = document.getElementById('subtractButton');
    addButton.addEventListener('click', function() {
        inputField.value = parseInt(inputField.value) + 1;
        inputField2.value = parseInt(inputField2.value) + 1;
        inputField1.value = parseInt(inputField1.value) + 1;
    });

    subtractButton.addEventListener('click', function() {
        var currentValue = parseInt(inputField.value);
        var currentValue2 = parseInt(inputField2.value);
        var currentValue1 = parseInt(inputField1.value);
        if (currentValue > 1) {
            inputField.value = currentValue - 1;
        }
        if (currentValue2 > 1) {
            inputField2.value = currentValue2 - 1;
        }
        if (currentValue1 > 1) {
            inputField1.value = currentValue1 - 1;
        }
    });
});

function getData(sl, id,idms) {


    console.log("Số lượng: " + sl + ", ID: " + id);
    var hienslgoc1 = document.getElementById('hiengoc1');
    var input = document.getElementById("inputid");
    var input1 = document.getElementById("inputid1");
    var hiensl = document.getElementById("hiensl");
    var mausac = document.getElementById("mauSac");
    var mausac1 = document.getElementById("mauSac1");



    console.log("Input element: ", input);
    console.log("Input1 element: ", input1);
    console.log("Hiensl element: ", hiensl);
    console.log("MauSac element: ", idms);
    hiensl.innerHTML = sl;
    input.value = id;
    input1.value = id;
    mausac.value = idms;
    mausac1.value = idms;
    hienslgoc1.value = sl;

}








