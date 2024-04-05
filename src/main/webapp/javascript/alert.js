function alertFunc() {
    if (confirm("Are you sure you want to delete this item?") === false) {
        return false;
    } else if (confirm("Are you sure you want to delete this item?") === true) {
        return true;
    }
}

