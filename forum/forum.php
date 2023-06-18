<?php
ob_start(); // Inicia o buffer de saída
$title = 'Início'; // Define o título da página
?>

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
                                        <a class="nav-link link-secondary ms-3" href="topico.php">Tópico #' . $j . '</a>
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
                            <form action="" method="post">
                                <div class="row mb-3">
                                    <div class="col-4">
                                        <label for="inputDescricao" class="form-label">Descrição</label>
                                        <input type="text" id="inputDescricao" class="form-control" aria-labelledby="descricaoHelpBlock">
                                    </div>
                                    <div class="col-8">
                                        <label for="inputDescricao" class="form-label">Enunciado</label>
                                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-primary">Cadastrar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="card">
                        <div class="card-body">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Título</th>
                                        <th scope="col">Autor</th>
                                        <th scope="col">Data de Cadastro</th>
                                        <th scope="co1" class="col-1">Ações</th>
                                    </tr>
                                </thead>
                                <tbody class="table-group-divider">
                                    <?php
                                    date_default_timezone_set('UTC');

                                    for ($i = 1; $i <= $m; $i++) {
                                        echo '
                                                <tr>
                                                    <th scope="row">' . $i . '</th>
                                                    <td><a href="topico.php">Tópico #' . $i . '</a></td>
                                                    <td>@raryson.fred</td>
                                                    <td>' . date("Y-m-d H:i:s") . '</td>
                                                    <td>
                                                        <div class="dropdown">
                                                            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Ações</button>
                                                            <ul class="dropdown-menu">
                                                                <li><a class="dropdown-item" href="#">Fixar</a></li>
                                                                <li><a class="dropdown-item" href="#">Fechar</a></li>
                                                            </ul>
                                                        </div>
                                                    </td>
                                                </tr>
                                            ';
                                    }
                                    ?>
                                </tbody>
                            </table>
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item">
                                        <a class="page-link" href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item">
                                        <a class="page-link" href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>

                <!--<div class="row">
                        <h1 class="h3 mt-3 mb-3">Visualização Usuário</h1>
                        <?php
                        /*for ($i = 1; $i <= 4; $i++) {
                            echo '
                                <div class="col-lg-6 col-md-4 col-sm-6 mb-4">
                                    <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">Tópico #' . $i . '</h5>
                                        <p class="card-text">Este é o conteúdo do Tópico #' . $i . '.</p>
                                        <div class="d-flex justify-content-end">
                                            <a href="#" class="btn btn-primary">Detalhar</a>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                            ';
                        }*/
                        ?>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>-->
            </div>
        </div>
    </div>
</div>

<?php
$content = ob_get_clean(); // Armazena o conteúdo do buffer de saída na variável $content
include('../layout.php'); // Inclui o layout
?>