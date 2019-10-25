from flask import Flask, render_template, request
from werkzeug import secure_filename
import os

app = Flask(__name__)
FILE_DIR = "audio"
@app.route('/uploader', methods=['GET', 'POST'])
def upload_file():
    if request.method == 'POST':
        f = request.files['file']
        if not os.path.exists(FILE_DIR):
            os.makedirs(FILE_DIR)
        f.save(os.path.join(FILE_DIR, secure_filename(f.filename)))
        return 'file uploaded successfully'+secure_filename(f.filename)
    return 'Not Authorized'


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')