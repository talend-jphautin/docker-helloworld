#!/usr/bin/python2.7
# -*- coding: utf-8 -*-
__author__ = 'jean-philippe_hautin'

import sys
import subprocess
from itertools import ifilter, groupby, ifilterfalse


class DockerImageDescription:
    def __init__(self, description):
        image = description.split()
        self.name = image[0]
        self.version = image[1]
        self.uid = image[2]

    def __str__(self):
        return 'name=%s, version=%s, uid=%s' % (self.name, self.version, self.uid)


def list_docker_images():
    cmd = 'docker images'
    stdout = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE).stdout
    images = []
    for line in stdout:
        images.append(DockerImageDescription(line))
    return images


def find_obsolete_talend_images(talend_images):
    temp = sorted(talend_images, key=by_name)
    images_by_names = groupby(temp, by_name)
    obsoletes = []
    for group_name, group_iter in images_by_names:
        group = [item for item in group_iter]
        latest = ifilter(is_latest_image, group).next()
        possible_latest_real = []
        possible_latest_real = [image for image in ifilter(lambda i: i.uid == latest.uid, group)]
        obsoletes_of_group = [image for image in ifilterfalse(lambda i: i.uid == latest.uid, group)]
        if len(possible_latest_real) > 0:
            latest_real = possible_latest_real[0].version
        else:
            latest_real = ''
        print "%50s    %8s    %30s   %15s    %2d" % (
        group_name, latest.version, latest_real, latest.uid, len(obsoletes_of_group))
        obsoletes += obsoletes_of_group
    return obsoletes


def remove_docker_image(image):
    cmd = 'docker rmi %s' % image.uid
    print subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE).stdout


def remove_docker_images(images):
    if len(images) > 0:
        print
        print "removing obsoletes images..."
        for image in images:
            print "    removing image %s" % image
            remove_docker_image(image)


def is_talend_image(image):
    return 'talend' in image.name and (image.version.startswith('1-') or is_latest_image(image))


def is_latest_image(image):
    return 'latest' in image.version


def by_name(image):
    return image.name


if __name__ == "__main__":
    images = list_docker_images()
    talend_images = ifilter(is_talend_image, images)
    obsolete_images = find_obsolete_talend_images(talend_images)
    remove_docker_images(obsolete_images)
