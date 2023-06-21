<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    <style>
        .dark-theme {
            background-color: #343a40;
            color: #ffffff;
        }
    </style>
</head>

<body class="light-theme">
    <nav class="navbar navbar-expand-lg fixed-top bg-body-tertiary">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a class="navbar-brand" href="../index.php">Monitorando</a>
                <div class="collapse navbar-collapse justify-content-end">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link link-primary" href="forum.php">Fórum</a>
                        </li>
                    </ul>
                    <!-- Form Switch para mudar o tema -->
                    <div class="form-check form-switch">
                        <input class="form-check-input bg-body-tertiary shadow-none" type="checkbox" id="themeSwitch" onchange="toggleTheme(this)">
                        <label class="form-check-label" for="themeSwitch"></label>
                    </div>
                </div>
            </div>
        </div>
    </nav>

    <div class="container-fluid mt-5 py-2">
        <div class="row">
            <nav class="col-2 menu-vertical d-flex flex-column vh-100 overflow-auto">
                <ul class="nav flex-column ">
                    <?php
                    $n = rand(1, 5);
                    for ($i = 1; $i <= $n; $i++) {
                        echo '
                            <li class="nav-item">
                                <a class="nav-link" href="#">Turma #' . $i . '</a>
                            ';
                        $m = rand(0, 5);
                        for ($j = 1; $j <= $m; $j++) {
                            echo '
                                <ul class="nav flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link link-secondary ms-3" href="#">Tópico #' . $j . '</a>
                                    </li>
                                </ul>';
                        }
                        echo '</li>
                        ';
                    }
                    ?>
                </ul>
            </nav>
            <div class="col-10">
                <div class="container">
                    <div class="row">
                        <div class="d-flex justify-content-end">
                            <h1 class="mt-3 mb-3">Turma <?php echo '#' . $n; ?></h1>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h3>Tópico #<?php echo $m; ?></h3>
                                <p style="text-align: justify;">
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed est justo, finibus eu porttitor nec, eleifend vel erat. Vestibulum vehicula vestibulum ultrices. Nunc faucibus finibus lorem ut venenatis. Aliquam erat volutpat. Quisque vitae tristique augue, vel vehicula libero. Curabitur pretium, justo a iaculis scelerisque, nulla ligula faucibus dui, id blandit purus purus id lorem. Vivamus sed porta enim. Nullam consequat molestie vehicula. Nunc sagittis, quam a porta malesuada, mi ex semper elit, ac sodales nisl erat ut risus. Suspendisse sit amet ligula non quam ornare feugiat. In nec viverra leo, vitae eleifend urna. Quisque molestie lectus ut nulla imperdiet laoreet vitae non leo. Integer vel dui maximus, ornare risus interdum, euismod risus. Etiam elit lacus, elementum sed massa ac, faucibus gravida augue.
                                </p>
                                <label for="inputDescricao" class="form-label">Resposta</label>
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                                <div class="row mt-3">
                                    <div class="col">
                                        <a class="icon-link" href="#">
                                            Icon link
                                            <svg class="bi" aria-hidden="true">
                                                <use xlink:href="#arrow-right"></use>
                                            </svg>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <div class="d-flex justify-content-end">
                                            <a class="btn btn-primary" href="forum/forum.php">Responder</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <footer class="py-5 mt-3 bg-body-tertiary">
        <div class="container">
            <div class="row">
                <div class="col-6 col-md-2 mb-3">
                    <h5>Section</h5>
                    <ul class="nav flex-column">
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Home</a></li>
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Features</a></li>
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Pricing</a></li>
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">FAQs</a></li>
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">About</a></li>
                    </ul>
                </div>

                <div class="col-6 col-md-2 mb-3">
                    <h5>Section</h5>
                    <ul class="nav flex-column">
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Home</a></li>
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Features</a></li>
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Pricing</a></li>
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">FAQs</a></li>
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">About</a></li>
                    </ul>
                </div>

                <div class="col-6 col-md-2 mb-3">
                    <h5>Section</h5>
                    <ul class="nav flex-column">
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Home</a></li>
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Features</a></li>
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Pricing</a></li>
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">FAQs</a></li>
                        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">About</a></li>
                    </ul>
                </div>

                <div class="col-md-5 offset-md-1 mb-3">
                    <form>
                        <h5>Subscribe to our newsletter</h5>
                        <p>Monthly digest of what's new and exciting from us.</p>
                        <div class="d-flex flex-column flex-sm-row w-100 gap-2">
                            <label for="newsletter1" class="visually-hidden">Email address</label>
                            <input id="newsletter1" type="text" class="form-control" placeholder="Email address">
                            <button class="btn btn-primary" type="button">Subscribe</button>
                        </div>
                    </form>
                </div>
            </div>

            <div class="d-flex flex-column flex-sm-row justify-content-between py-4 my-4 border-top">
                <p>© 2023 Company, Inc. All rights reserved.</p>
                <ul class="list-unstyled d-flex">
                    <li class="ms-3"><a class="link-body-emphasis" href="#"><svg class="bi" width="24" height="24">
                                <use xlink:href="#twitter"></use>
                            </svg></a></li>
                    <li class="ms-3"><a class="link-body-emphasis" href="#"><svg class="bi" width="24" height="24">
                                <use xlink:href="#instagram"></use>
                            </svg></a></li>
                    <li class="ms-3"><a class="link-body-emphasis" href="#"><svg class="bi" width="24" height="24">
                                <use xlink:href="#facebook"></use>
                            </svg></a></li>
                </ul>
            </div>
        </div>
    </footer>

    <script src="../js/script.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>

</html>