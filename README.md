# SortFromFiles

Программа SortFromFiles выполняет сортировку слиянием из нескольких предварительно отсортированных файлов, указанных в аргументах командной строки.
Доступна сортировка строк либо целых чисел по возрастанию или по убыванию.
Данные не соотвтсвующего типа или нарушающие предварительную сортировку внутри файла будут проигнорированы и не попадут в выходной файл, но дальнейшие данные так же будут отсортированы.
Строкой считается любая не пустая строка файла, числом - только строка, являющаяся целым числом.
К программе прилагаются jUnit тесты.
