function PostMethod(){

}

function attentionAlert(expAttention) {
    const attention = document.getElementById('attention'); // 获取弹窗元素
    attention.textContent = expAttention; // 设置弹窗的文本内容
    attention.classList.add('show-pop');

    // 添加动画结束事件监听器
    attention.addEventListener('animationend', function() {
        attention.classList.remove('show-pop'); // 动画结束后移除类，重置状态
    }, { once: true }); // 设置为一次性监听，避免重复触发

}


function dialogAlert(msg){
    const $dialog = $('#dialog'); // 获取弹窗元素 (jQuery 对象)
    $dialog.text(msg); // 设置弹窗的文本内容
    $dialog.addClass('show-pop'); // 添加show-popup类，触发动画

    // 添加动画结束事件监听器
    $dialog.one('animationend', function() {
        $dialog.removeClass('show-pop'); // 动画结束后移除show-popup类，重置状态
    });
}


function expAlert(expAttention) {
    const popup = document.getElementById('popup'); // 获取弹窗元素
    popup.textContent = expAttention; // 设置弹窗的文本内容
    popup.classList.add('show-pop'); // 添加show-popup类，触发动画
    localStorage.removeItem('expAttention')

    // 添加动画结束事件监听器
    popup.addEventListener('animationend', function() {
        popup.classList.remove('show-pop'); // 动画结束后移除show-popup类，重置状态
    }, { once: true }); // 设置为一次性监听，避免重复触发

}



function showConfirmDialog(message, id, onConfirm) {
    // 设置提示消息
    $("#pop-confirm").find("p").text(message);
    // 设置删除 ID
    $("#pop-confirm").find("input[class='btn btn-danger']").attr("deleteId", id);
    // 显示弹窗和遮罩层
    $("#pop-confirm").addClass('active');
    $('#overlay').addClass('active');

    // 绑定“确定”按钮的点击事件，执行回调函数
    $("#pop-confirm").find("input[class='btn btn-danger']").off('click').on('click', function () {
        onConfirm(id); // 调用传入的回调函数，传递 id
        closePopConfirm(); // 点击“确定”后关闭弹窗
    });

    // 添加按下 ESC 键关闭弹窗的事件监听器
    $(document).off('keydown.closeConfirm').on('keydown.closeConfirm', function (event) {
        if (event.key === "Escape") {
            closePopConfirm();
        }
    });
}

// 关闭弹窗
function closePopConfirm() {
    $("#pop-confirm").removeClass('active');
    $('#overlay').removeClass('active');
    // 移除按下 ESC 键时关闭弹窗的事件监听器
    $(document).off('keydown.closeConfirm');
    $("#pop-confirm").find("input[class='btn btn-danger']").removeAttr("deleteId");
    // 移除“确定”按钮的点击事件，避免重复绑定
    $("#pop-confirm").find("input[class='btn btn-danger']").off('click');
}


