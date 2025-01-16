<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header.jsp"/>

<style>


    .section-title {
        font-size: 1.8em;
        margin-bottom: 10px;
    }

    .paragraph {
        font-size: 1.1em;
        line-height: 1.6em;
        margin-bottom: 20px;
    }

    footer {
        text-align: center;
        background-color: #333;
        color: white;
        padding: 10px 0;
        position: fixed;
        width: 100%;
        bottom: 0;
    }

    .faq {
        margin-bottom: 15px;
    }
    .question {

        color: white;
        padding: 10px;
        cursor: pointer;
        font-weight: bold;
        border-radius: 5px;
    }
    .answer {
        display: none;
        padding: 10px;
        margin-top: 10px;
        border-radius: 5px;

    }
</style>

<div class="content">
    <h2 class="section-title">About Us</h2>
    <p class="paragraph">
        Welcome to the Car Parking Application! Our goal is to provide an easy and efficient way for drivers to find and manage parking spaces in busy areas.
        Whether you're looking for a spot in a crowded city or other spaces, our application helps you quickly find available spaces,
        reserve spots, and even pay for parking directly through your phone.
    </p>

    <h2 class="section-title">Features</h2>
    <p class="paragraph">
        Our Car Parking App comes with a range of features designed to make parking simpler:
    </p>
    <ul class="paragraph">
        <li>Real-time availability of parking spots</li>
        <li>Reserve parking space in advance</li>
        <li>Multiple payment options</li>
        <li>Map integration for easy navigation</li>
        <li>Parking history and receipts</li>
    </ul>

    <h2 class="section-title">Why Choose Us?</h2>
    <p class="paragraph">
        We understand the hassle of finding parking in crowded areas, and we aim to eliminate that frustration with our easy-to-use application.
        With real-time updates, reservation capabilities, and secure payments, we ensure a smooth parking experience every time.
    </p>


    <div style="align-items: center;">
        <h1>Frequently Asked Questions</h1>

        <div class="faq">
            <div class="question">1. How do I find a parking spot near me?</div>
            <div class="answer">You can use our website or mobile app to search for available parking spots in your area.
                Simply enter your location or the address of your destination, and we'll show you nearby options.</div>
        </div>

        <div class="faq">
            <div class="question">2. Can I reserve a parking space in advance?</div>
            <div class="answer">Yes, you can reserve a parking spot in advance using our website or app.
                Choose your preferred location, time, and duration, and we'll ensure a spot is available when you arrive.</div>
        </div>

        <div class="faq">
            <div class="question">3. How do I pay for parking?</div>
            <div class="answer">We offer multiple payment options, including credit/debit cards, mobile wallets, and in-app payments. You can pay directly through our website or app when reserving a spot or upon arrival at the parking facility.</div>
        </div>

        <div class="faq">
            <div class="question">4. Is my payment refundable if I cancel my reservation?</div>
            <div class="answer">Cancellation policies vary depending on the parking provider. Please check the terms and conditions when making a reservation. Typically, cancellations made in advance may qualify for a partial refund.</div>
        </div>

        <script>
            document.querySelectorAll('.question').forEach(function (question) {
                question.addEventListener('click', function () {
                    const answer = this.nextElementSibling;
                    answer.style.display = (answer.style.display === 'block') ? 'none' : 'block';
                });
            });
        </script>








<jsp:include page="../include/footer.jsp"/>
