<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inicio de Sesión</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome para el icono del ojo y los botones -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body, html {
            height: 100%;
            margin: 0;
            overflow: hidden;
            transition: background-color 0.5s, color 0.5s; /* Transición suave */
        }
        #particles-js {
            position: absolute;
            width: 100%;
            height: 100%;
            background-image: url('https://img.freepik.com/foto-gratis/hermosa-escena-dibujos-animados-paisajes-anime_23-2151035237.jpg?t=st=1730694471~exp=1730698071~hmac=674b25c09863dfa67d54beb44cded430907b042e7edaf5e4bdf9985c7875a6c3&w=900');
            background-size: cover;
            background-position: center;
            transition: background-image 0.5s; /* Transición suave para el fondo */
        }
        .navbar {
            background-color: transparent;
            position: relative;
            z-index: 1000;
        }
        .navbar-brand, .nav-link {
            color: white !important;
            font-size: 1.2rem;
        }
        .login-container {
            position: relative;
            z-index: 1000;
            margin-top: 100px;
            background-color: rgba(255, 255, 255, 0.5);
            border-radius: 10px;
            padding: 30px;
            transition: background-color 0.5s; /* Transición suave */
        }
        .form-control {
            background-color: rgba(255, 255, 255, 0.5);
            border: none;
            color: black;
        }
        .form-control::placeholder {
            color: rgba(0, 0, 0, 0.7);
        }
        .btn-custom {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-custom:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .password-container {
            position: relative;
        }
        .password-toggle {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            cursor: pointer;
            color: black;
        }
        .login-title {
            color: #333333;
            font-weight: bold;
        }
        .footer {
            position: fixed;
            left: 0;
            bottom: 0;
            width: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            color: white;
            text-align: center;
            padding: 10px 0;
            z-index: 1000;
        }

        /* Estilos para el modo oscuro */
        body.dark-mode {
            background-color: #121212;
            color: white;
        }
        body.dark-mode #particles-js {
            background-image: url('https://wallpapers.com/images/hd/your-name-stars-and-sky-bcxgzaqjzc9wbloq.webp'); /* Cambia a la imagen deseada */
            background-size: cover;
            background-position: center;
        }
        body.dark-mode .login-container {
            background-color: rgba(50, 50, 50, 0.7);
        }
        body.dark-mode .form-control {
            background-color: rgba(255, 255, 255, 0.2);
            color: white;
        }
        body.dark-mode .form-control::placeholder {
            color: rgba(255, 255, 255, 0.7);
        }
 body.dark-mode .navbar-brand, body.dark-mode .nav-link {
            color: white !important;
        }
        body.dark-mode .footer {
            background-color: transparent;
            color: white;
        }
        body.dark-mode .login-title {
            color: white;
        }
    </style>
</head>
<body>
    <div id="particles-js"></div>

    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">InmobiliariaSA</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Acerca de</a>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" id="dark-mode-btn">Modo Oscuro</button>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row justify-content-end">
            <div class="col-md-6 col-lg-4">
                <div class="login-container">
                    <h2 class="login-title mb-4">Iniciar Sesión</h2>
                    <form action="LoginController" method="POST">
                        <div class="mb-3">
                            <input type="text" class="form-control" name="username" placeholder="Usuario" required>
                        </div>
                        <div class="mb-3 password-container">
                            <input type="password" class="form-control" id="password" name="password" placeholder="Contraseña" required>
                            <i class="fas fa-eye password-toggle" id="togglePassword"></i>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-custom btn-block text-white">Iniciar Sesión</button>
                        </div>
                    </form>
                    <% if (request.getAttribute("errorMessage") != null) { %>
                        <p class="text-danger text-center mt-3"><%= request.getAttribute("errorMessage") %></p>
                    <% } %>
                </div>
            </div>
        </div>
    </div>

    <footer class="footer">
        <div class="container">
            <p>Este proyecto tiene como objetivo proporcionar una plataforma eficiente para la gestión inmobiliaria.</p>
            <p>&copy; 2024 InmobiliariaSA. Todos los derechos reservados.</p>
        </div>
    </footer>

    <!-- Particles.js -->
    <script src="https://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script>
    <!-- Bootstrap JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <script>
        particlesJS('particles-js', {
            particles: {
                number: { value: 80, density: { enable: true, value_area: 800 } },
                color: { value: "#ffffff" },
                shape: { type: "circle" },
                opacity: { value: 0.5, random: false },
                size: { value: 3, random: true },
                line_linked: { enable: true, distance: 150, color: "#ffffff", opacity: 0.4, width: 1 },
                move: { enable: true, speed: 6, direction: "none", random: false, straight: false, out_mode: "out", bounce: false }
            },
            interactivity: {
                detect_on: "canvas",
                events: { onhover: { enable: true, mode: "repulse" }, onclick: { enable: true, mode: "push" }, resize: true },
                modes: { grab: { distance: 400, line_linked: { opacity: 1 } }, bubble: { distance: 400, size: 40, duration: 2, opacity: 8, speed: 3 }, repulse: { distance: 200, duration: 0.4 }, push: { particles_nb: 4 }, remove: { particles_nb: 2 } }
            },
            retina_detect: true
        });

        const togglePassword = document.querySelector('#togglePassword');
        const password = document.querySelector('#password ');

        togglePassword.addEventListener('click', function (e) {
            const type = password.getAttribute('type') === 'password ' ? 'text' : 'password';
            password.setAttribute('type', type);
            this.classList.toggle('fa-eye-slash');
        });

        const darkModeBtn = document.querySelector('#dark-mode-btn');

        darkModeBtn.addEventListener('click', function (e) {
            document.body.classList.toggle('dark-mode');
            if (document.body.classList.contains('dark-mode')) {
                this.textContent = 'Modo Claro';
            } else {
                this.textContent = 'Modo Oscuro';
            }
        });
    </script>
</body>
</html>