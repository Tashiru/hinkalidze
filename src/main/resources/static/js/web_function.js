function displayImage(inputElement) {
    const file = inputElement.files[0];
    const imageURL = URL.createObjectURL(file);
    document.getElementById('preview').src = imageURL;
    inputElement.value = null;
    document.getElementById('preview').onload = () => URL.revokeObjectURL(imageURL);
}