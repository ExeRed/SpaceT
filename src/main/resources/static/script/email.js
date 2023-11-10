$(document).ready(function() {
    // Обработка отправки формы
    $("#subscribeForm").submit(function(e) {
        e.preventDefault(); // Отменить стандартное поведение формы

        // Отправить AJAX-запрос
        $.ajax({
            url: "/subscribe",
            type: "POST",
            data: $(this).serialize(),
            success: function() {
                // Показать сообщение об успешной подписке
                $("#subscribeForm").hide();
                $("#subscribeMessage").show();

                // Задержка перед скрытием сообщения
                setTimeout(function() {
                    $("#subscribeMessage").hide();
                    $("#subscribeForm").show();
                }, 5000); // Скрыть сообщение через 3 секунды (3000 миллисекунд)
            }
        });
    });
});