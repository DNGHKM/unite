$(document).ready(function () {
    $.ajax({
        url: contextPath + "/api/currency",
        method: "GET",
        dataType: "json",
        success: function (data) {
            const rates = {
                CNH: "#cnh-rate .rate-value",
                EUR: "#eur-rate .rate-value",
                "JPY(100)": "#jpy-rate .rate-value",
                USD: "#usd-rate .rate-value"
            };

            data.forEach(function (currency) {
                if (rates[currency.cur_unit]) {
                    $(rates[currency.cur_unit]).text(currency.deal_bas_r+' 원'); // 숫자만 업데이트
                }
            });
        },
        error: function () {
            console.error("Error fetching currency data.");
            $("#currency-list .rate-value").text("데이터를 불러올 수 없습니다.");
        }
    });
});
