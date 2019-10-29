import os
import sys

sys.path.append(os.path.join(os.path.dirname(__file__), "language_detection"))
from flask import Flask, request
from werkzeug import secure_filename

from language_detection import cli
from utils import *
import math

app = Flask(__name__)
FILE_DIR = "audio"


@app.route('/uploader', methods=['GET', 'POST'])
def upload_file():
    if request.method == 'POST':
        f = request.files['file']
        uploaded_audio = os.path.join(FILE_DIR, secure_filename(f.filename))
        if not os.path.exists(FILE_DIR):
            os.makedirs(FILE_DIR)
        f.save(uploaded_audio)
        mp3_fname = "{}.mp3".format(uploaded_audio)
        convert_to_mp3(uploaded_audio, mp3_fname)
        audio_range = get_mpeg_audio_length(mp3_fname)
        if audio_range < 10:
            concat_mpeg_audio(mp3_fname, "{}.mp3".format(mp3_fname), math.ceil(10.0 / audio_range))
            os.remove(mp3_fname)
            mp3_fname = "{}.mp3".format(mp3_fname)
        # process cli file
        analyze_audio_type(mp3_fname)
        # os.remove(uploaded_audio)
        return 'file uploaded successfully' + secure_filename(f.filename)
    return 'Not Authorized'


def analyze_audio_type(fname, model=os.path.join("language_detection", "model.h5"), keep_temp_files=False):
    normalized_file, normalized_dir = cli.normalize(fname, 0.1, 0.5, False)
    samples, samples_dir = cli.load_samples(normalized_file)

    if keep_temp_files:
        cli.clean((normalized_dir, samples_dir))

    scores, languages = cli.predict(model)

    total = np.sum(scores)
    for language_idx, language in enumerate(languages):
        score = scores[language_idx]
        print("{language}: {percent:.2f}% ({amount:.0f})".format(
            language=language,
            percent=(score / total) * 100,
            amount=score))


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
