<%@page contentType="text/html" pageEncoding="UTF-8"%>
<footer class="footer" style="padding-top: 0px; padding-left: 0px; padding-right: 0px;">

    <!-- Slider noticias -->
    <div class="footer-news-marquee" id="news-marquee">
        <div class="news-cards" id="news-cards">
            <div class="news-card">Acaba la Feria de Abril con ganancias</div>
            <div class="news-card">Evento cultural este fin de semana en Plaza Nueva</div>
            <div class="news-card">Nueva campaña de reciclaje en Sevilla</div>
            <div class="news-card">Obras de mejora en el centro histórico</div>
            <div class="news-card">Inauguración del parque tecnológico</div>
            <div class="news-card">Acaba la Feria de Abril con ganancias</div>
            <div class="news-card">Evento cultural este fin de semana en Plaza Nueva</div>
            <div class="news-card">Nueva campaña de reciclaje en Sevilla</div>
            <div class="news-card">Obras de mejora en el centro histórico</div>
            <div class="news-card">Inauguración del parque tecnológico</div>
            <div class="news-card">Acaba la Feria de Abril con ganancias</div>
            <div class="news-card">Evento cultural este fin de semana en Plaza Nueva</div>
            <div class="news-card">Nueva campaña de reciclaje en Sevilla</div>
            <div class="news-card">Obras de mejora en el centro histórico</div>
            <div class="news-card">Inauguración del parque tecnológico</div>
        </div>
    </div>

    <!-- Imagen fondo ciudad sevilla -->
    <div class="footer-skyline">
        <img src="img/skyline.png" alt="Skyline de Sevilla" />
    </div>
    <!-- INfo footer -->
    <div class="footer-info">
        <p>Ayuntamiento de Sevilla. Plaza Nueva, 1 - C.P. 41001 | Teléfono 010 - 955 010 010</p>
        <p>Proyecto desarrollado por Enrique Machado</p>
    </div>
    <div class="footer-redes">
        <a href="https://www.facebook.com/ayto.sevilla" target="_blank"><img src="img/fb.png" alt="Facebook" /></a>
        <a href="https://twitter.com/Ayto_Sevilla" target="_blank"><img src="img/insta.png" alt="Twitter" /></a>
        <a href="https://www.instagram.com/ayto_sevilla/" target="_blank"><img src="img/x.png" alt="Instagram" /></a>
    </div>

    <!-- style footer para las cards de las noticias que deslizan -->
    <style>
        .footer-news-marquee {
            background: white;
            color: black;
            padding: 15px 10px 15px 10px;
            margin: 10px auto 0 auto; /* arriba 10px, abajo 0 para pegar con imagen */
            max-width: 100%;
            font-family: 'Arial', sans-serif;
            overflow: hidden;
            position: relative;
            height: 100px;
            display: flex;
            align-items: center;
            white-space: nowrap;
        }

        .news-cards {
            display: inline-flex;
            animation: scroll-left 50s linear infinite;
        }

        .news-card {
            background: #f0f0f0;
            border-radius: 8px;
            padding: 10px 20px;
            margin-right: 15px;
            color: #940444;
            font-weight: 600;
            box-shadow: 0 1px 3px rgba(0,0,0,0.15);
            white-space: normal;
            min-width: 250px;
        }

        @keyframes scroll-left {
            0% {
                transform: translateX(100%);
            }
            100% {
                transform: translateX(-100%);
            }
        }

        .footer-skyline {
            margin-top: 0;
            padding-top: 0;
        }

        .footer-skyline img {
            width: 100%;
            height: auto;
            display: block;
            margin: 0;
            padding: 0;
        }

        /* Footer info y redes (mantener igual si quieres) */
        .footer-info {
            padding: 15px 10px;
            font-size: 0.9em;
            color: white;
            text-align: center;
        }

        .footer-redes {
            text-align: center;
            padding: 10px 0 20px 0;
        }

        .footer-redes a img {
            width: 30px;
            height: 30px;
            margin: 0 10px;
            vertical-align: middle;
        }
    </style>

  

</footer>
