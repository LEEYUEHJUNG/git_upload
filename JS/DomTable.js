window.onload = function () {
    document.getElementById('addButton').onclick = addRow;
    document.getElementById('clearButton').onclick = clearFields;
    document.getElementById('modifyButton').onclick = modifyRow;

    let selectedRow = null;
    const fields = ['manufacturer', 'category', 'cost', 'price'];

    function addRow() {
        const values = fields.map(function (field) {
            return document.getElementById(field).value;
        });

        if (values[0] === '' || values[1] === '') {
            alert('製造商或類別不可空白');
            return;
        }

        const table = document.getElementById('resultTable').getElementsByTagName('tbody')[0];
        const newRow = table.insertRow();
        const index = table.rows.length;

        let rowContent = '<td>' + index + '</td>' +
            '<td><input type="radio" name="selectRow" onclick="selectRow(this)"></td>';
        fields.forEach(function (field, i) {
            rowContent += '<td>' + values[i] + '</td>';
        });

        rowContent += '<td><button onclick="deleteRow(this)">刪除</button></td>';

        newRow.innerHTML = rowContent;
        clearFields();
    }

    function clearFields() {
        fields.forEach(function (field) {
            document.getElementById(field).value = '';
        });
    }

    window.deleteRow = function (button) {
        const row = button.parentNode.parentNode;
        row.parentNode.removeChild(row);
        resetRowNumbers();
    }

    function resetRowNumbers() {
        const rows = document.getElementById('resultTable').getElementsByTagName('tbody')[0].rows;
        for (let i = 0; i < rows.length; i++) {
            rows[i].cells[0].innerText = i + 1;
        }
    }

    window.selectRow = function (radio) {
        selectedRow = radio.parentNode.parentNode;
        fields.forEach(function (field, index) {
            document.getElementById(field).value = selectedRow.cells[index + 2].innerText;
        });
    }

    function modifyRow() {
        const values = fields.map(function (field) {
            return document.getElementById(field).value;
        });

        if (values[0] === '' || values[1] === '') {
            alert('製造商或類別不可空白');
            return;
        }

        if (selectedRow) {
            fields.forEach(function (field, index) {
                selectedRow.cells[index + 2].innerText = values[index];
            });
        }

        clearFields();
        selectedRow = null;
    }
}
