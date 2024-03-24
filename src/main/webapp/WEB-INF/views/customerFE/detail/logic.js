

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
    var input = document.getElementById("inputid");
    var input1 = document.getElementById("inputid1");
    var hiensl = document.getElementById("hiensl");
    var mausac = document.getElementById("mauSac");

    console.log("Input element: ", input);
    console.log("Input1 element: ", input1);
    console.log("Hiensl element: ", hiensl);
    console.log("MauSac element: ", idms);
    hiensl.innerHTML = sl;
    input.value = id;
    input1.value = id;
    mausac.value = idms;
}



var previousSelectedId = null;
function getDataForColor(idsp, idms) {

    var element = document.getElementById('mausac'+idms);

    if (previousSelectedId !== null) {
        var previousSelected = document.getElementById('mausac'+previousSelectedId);
        previousSelected.classList.remove('red-background'); // Xóa class red-background từ nút trước đó
    }

        element.classList.add('red-background'); // Thêm class red-background cho nút hiện tại
        previousSelectedId = idms;


    console.log(idsp);
    console.log(idms);
    $.ajax({
        type: "GET",
        url: "/hienthiKCCus/" + idsp + "/" + idms,
        success: function(response) {
            $('#productsContainer').empty();

            // Lặp qua danh sách dữ liệu và tạo thẻ <a> cho mỗi mục
            $.each(response, function(index, data) {
                var idkc = data[0]; // Giả sử thông tin thứ nhất ở vị trí đầu tiên
                var ten = data[1]; // Giả sử thông tin thứ hai ở vị trí thứ hai
                var soLuong = data[2]; // Giả sử thông tin thứ hai ở vị trí thứ hai

                // Tạo thẻ <a> với các thông tin và gán sự kiện onclick
                var linkHTML = '<a id="kichco' + idkc + '" data-info="' + idkc + ',' + ten + '" onclick="getData(' + soLuong + ',\'' + idkc + '\',\'' + idms + '\')" class="btn btn-success" style="margin-left: 30px">' + ten+ '</a>';

                // Thêm thẻ <a> vào container
                $('#productsContainer').append(linkHTML);
                $('#productsContainer a').on('click', function() {
                    $('#productsContainer a').removeClass('red-background'); // Xóa class red-background từ tất cả các phần tử khác
                    $(this).addClass('red-background'); // Thêm class red-background cho phần tử được click
                });
            });
        },
        error: function(xhr, status, error) {
            // Xử lý lỗi
            console.error("Error occurred while fetching data: " + error);
        }
    });
}


