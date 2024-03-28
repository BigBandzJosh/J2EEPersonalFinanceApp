function alertFunc() {


   //if they don't want to delete the item, return false
    if (confirm("Are you sure you want to delete this item?") === false) {
        return false;
    } else if (confirm("Are you sure you want to delete this item?") === true) {
        return true;
    }




}