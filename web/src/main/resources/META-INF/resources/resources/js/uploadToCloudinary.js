var CLOUDINARY_URL = 'https://api.cloudinary.com/v1_1/dtz8xwoku/upload';
var CLOUDINARY_UPLOAD_PRESET = 't3bagpyk';
var fileUpload = document.getElementById('file-upload');

fileUpload.addEventListener('change', function (event){
    var file = event.target.files[0];
    var formData = new FormData();
    formData.append('file',file);
    formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET);

    axios({
        url: CLOUDINARY_URL,
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: formData
    }).then(function (res){
        //console.log(res.data.url.toString());
        document.getElementById("crear-producto:link_imagen").value  = res.data.url.toString();
        console.log(document.getElementById("crear-producto:link_imagen").value);
    }).catch(function (err){
        console.log(err);
    });
});