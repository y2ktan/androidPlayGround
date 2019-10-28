import contextlib
import shutil
import wave


def concat_audio(origin, concat_output, dup_count=1):
    destination = open(concat_output, 'wb')
    for count in range(dup_count):
        shutil.copyfileobj(open(origin, 'rb'), destination)
    destination.close()


def get_audio_length(fname):
    with contextlib.closing(wave.open(fname, 'r')) as f:
        frames = f.getnframes()
        rate = f.getframerate()
        duration = frames / float(rate)
        return duration
