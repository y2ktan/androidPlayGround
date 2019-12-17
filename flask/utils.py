import contextlib
import os
import shutil
import subprocess
import wave

import numpy as np
import scipy.io.wavfile
from mutagen.mp3 import MP3
from scipy import interpolate


def concat_mpeg_audio(origin, concat_output, dup_count=1):
    destination = open(concat_output, 'wb')
    for count in range(dup_count):
        shutil.copyfileobj(open(origin, 'rb'), destination)
    destination.close()


def concat_mpeg_audio2(origin, concat_output, dup_count=1):
    file_list = []
    for count in range(dup_count):
        file_list.append(origin)
    cmd = 'ffmpeg -y -i "concat:{}" -acodec copy {}'.format("|".join(file_list), concat_output)
    return os.system(cmd)
    # destination = open(concat_output, 'wb')
    # for count in range(dup_count):
    #     shutil.copyfileobj(open(origin, 'rb'), destination)
    # destination.close()


# import audiolab, scipy
# a, fs, enc = audiolab.wavread('file1.wav')
# b, fs, enc = audiolab.wavread('file2.wav')
# c = scipy.vstack((a,b))
# audiolab.wavwrite(c, 'file3.wav', fs, enc)
def concat_wav_audio(origin, concat_output, dup_count=1):
    os.system("ffmpeg -y -i {} -codec:a pcm_mulaw {}".format(origin, concat_output))
    input_file_cmd = "file '{}'\n".format(concat_output) * dup_count
    f = open("mylist.txt", "w")
    f.write(input_file_cmd)
    f.close()
    # print("ffmpeg {} -filter_complex '[0:0][1:0][2:0][3:0]concat=n={}:v=0:a=1[out]' -map '[out]' {}".format(input_file_cmd, dup_count,concat_output))
    print("ffmpeg -y -f concat -safe 0 -i {} -c copy {}".format("mylist.txt", concat_output))
    return os.system("ffmpeg -y -f concat -safe 0 -i {} -c copy {}".format("mylist.txt", concat_output))


def get_audio_length(fname):
    with contextlib.closing(wave.open(fname, 'r')) as f:
        frames = f.getnframes()
        rate = f.getframerate()
        duration = frames / float(rate)
    return duration


def resample_audio(fname, output, target_sample_rate=22050):
    rate, data = scipy.io.wavfile.read(fname)
    duration = data.shapen[0] / rate
    time_old = np.linspace(0, duration, data.shape[0])
    time_new = np.linspace(0, duration, int(data.shape[0] * target_sample_rate / rate))
    new_audio = interpolate(time_new).T
    scipy.io.wavfile.write(output)


def convert_to_mp3(fname, output):
    return subprocess.call(
        ["ffmpeg", "-y", "-i", fname, "-codec:a", "libmp3lame", "-qscale:a", "2", "-ar", "22050", "-ac", "2", output])


def get_mpeg_audio_length(fname):
    audio = MP3(fname)
    return audio.info.length
