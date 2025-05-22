<%-- 
    Document   : cabecera
    Created on : 16-may-2025, 16:17:20
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<footer class="footer" style="padding: 0;">

    <!-- Slider de noticias que se van repitiendo cuando acaban -->
    <div class="news-ticker-container">
        <div class="news-ticker-track" id="newsTickerTrack">
            <div class="news-item">📰 Acaba la Feria de Abril con ganancias</div>
            <div class="news-item">🎭 Evento cultural este fin de semana en Plaza Nueva</div>
            <div class="news-item">♻️ Nueva campaña de reciclaje en Sevilla</div>
            <div class="news-item">🚧 Obras de mejora en el centro histórico</div>
            <div class="news-item">🏢 Inauguración del parque tecnológico</div>
            <div class="news-item">📰 Acaba la Feria de Abril con ganancias</div>
            <div class="news-item">🎭 Evento cultural este fin de semana en Plaza Nueva</div>
            <div class="news-item">♻️ Nueva campaña de reciclaje en Sevilla</div>
            <div class="news-item">🚧 Obras de mejora en el centro histórico</div>
            <div class="news-item">🏢 Inauguración del parque tecnológico</div>
        </div>
    </div>

    <!-- Imagen fondo ciudad Sevilla -->
    <div class="footer-skyline">
        <img src="img/skyline.png" alt="Skyline de Sevilla" />
    </div>

    <!-- Información footer -->
    <div class="footer-info">
        <p>Ayuntamiento de Sevilla. Plaza Nueva, 1 - C.P. 41001 | Teléfono 010 - 955 010 010</p>
        <p>Proyecto desarrollado por Enrique Machado</p>
    </div>

    <!-- Redes sociales -->
    <div class="footer-redes">
        <a href="https://www.facebook.com/ayto.sevilla" target="_blank">
            <img src="https://cdn.jsdelivr.net/npm/simple-icons@v9/icons/facebook.svg" alt="Facebook" width="30" style="filter: invert(1);" />
        </a>
        <a href="https://twitter.com/Ayto_Sevilla" target="_blank">
            <img src="https://cdn.jsdelivr.net/npm/simple-icons@v9/icons/x.svg" alt="X" width="30" style="filter: invert(1);" />
        </a>
        <a href="https://www.instagram.com/ayto_sevilla/" target="_blank">
            <img src="https://cdn.jsdelivr.net/npm/simple-icons@v9/icons/instagram.svg" alt="Instagram" width="30" style="filter: invert(1);" />
        </a>
    </div>



    <!-- Estilos del slider y footer -->
    <style>
        .news-ticker-container {
            background: #fff;
            border-top: 2px solid #940444;
            overflow: hidden;
            white-space: nowrap;
            position: relative;
            padding: 20px 0;
        }

        .news-ticker-track {
            display: inline-block;
            animation: tickerScroll 80s linear infinite;
        }

        .news-item {
            display: inline-block;
            background: #f9f9f9;
            margin: 0 20px;
            padding: 12px 20px;
            border-radius: 8px;
            font-weight: 600;
            color: #940444;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            min-width: 300px;
        }


        @keyframes tickerScroll {
            0% { transform: translateX(0%); } /* Empieza desde donde ya se ven las noticias */
            100% { transform: translateX(-100%); }
        }


        .footer-skyline img {
            width: 100%;
            height: auto;
            display: block;
        }

        .footer-info {
            padding: 15px 10px;
            font-size: 0.9em;
            color: white;
            text-align: center;
        }

        .footer-redes {
            text-align: center;
            padding: 15px 0 25px 0;
        }

        .footer-redes a img {
            width: 30px;
            height: 30px;
            margin: 0 12px;
            filter: brightness(0) invert(1);
        }
    </style>

</footer>
