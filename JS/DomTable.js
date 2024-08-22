window.onload = function () {
    document.getElementById('addButton').onclick = addRow;
    document.getElementById('clearButton').onclick = clearTable;
    document.getElementById('modifyButton').onclick = modifyRow;

    let selectedRow = null;
    const fields = ['manufacturer', 'category', 'cost', 'price'];

    function addRow() {
        const values = fields.map(function (field) {
            return document.getElementById(field).value.trim(); 
        });

        if (!values[0].trim() || !values[1].trim() ) {
            alert('製造商或類別不可空白');
            return;
        }

        if (isNaN(values[2]) || isNaN(values[3]) || values[2] === '' || values[3] === '') {
            alert('底價和售價必須為數字');
            return;
        }

        const table = document.getElementById('resultTable').getElementsByTagName('tbody')[0];
        const newRow = table.insertRow();

        const cellIndex = newRow.insertCell(0);
        const index = table.rows.length;
        cellIndex.innerText = index;

        const cellRadio = newRow.insertCell(1);
        const radioInput = document.createElement('input');
        radioInput.type = 'radio';
        radioInput.name = 'selectRow';
        radioInput.onclick = function () { selectRow(this); };
        cellRadio.appendChild(radioInput);

        fields.forEach(function (field, i) {
            const cell = newRow.insertCell(i + 2);
            cell.innerText = values[i];
        });

        const cellDelete = newRow.insertCell(fields.length + 2);
        const deleteButton = document.createElement('button');
        deleteButton.innerText = '刪除';
        deleteButton.onclick = function () { deleteRow(this); };
        cellDelete.appendChild(deleteButton);

        clearFields();
    }

    function clearFields() {
        fields.forEach(function (field) {
            document.getElementById(field).value = '';
        });
    }

    function clearTable() {
        const table = document.getElementById('resultTable').getElementsByTagName('tbody')[0];
        table.innerHTML = ''; 
        clearFields(); 
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
            return document.getElementById(field).value.trim(); 
        });

        if (!values[0].trim() || !values[1].trim()) {
            alert('製造商或類別不可空白');
            return;
        }

        if (isNaN(values[2]) || isNaN(values[3]) || values[2] === '' || values[3] === '') {
            alert('底價和售價必須為數字');
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
